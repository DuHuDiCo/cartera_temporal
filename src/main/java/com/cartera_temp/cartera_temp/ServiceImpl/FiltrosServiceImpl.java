package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.ItemsFiltros;
import com.cartera_temp.cartera_temp.FeignClients.ClientesClient;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.FiltrosService;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class FiltrosServiceImpl implements FiltrosService {

    private final CuentasPorCobrarRepository cuentasPorCobrarRepository;
    private final usuario_client usuario_client;
    private final AsesorCarteraRepository asesorCarteraRepository;

    public FiltrosServiceImpl(CuentasPorCobrarRepository cuentasPorCobrarRepository, usuario_client usuario_client, AsesorCarteraRepository asesorCarteraRepository) {
        this.cuentasPorCobrarRepository = cuentasPorCobrarRepository;
        this.usuario_client = usuario_client;
        this.asesorCarteraRepository = asesorCarteraRepository;
    }

    
        
    
    
    @Override

    public ItemsFiltros sedeByUsuario(String username) {
        ItemsFiltros items = new ItemsFiltros();
        
        Usuario usuario = usuario_client.getUserByUsername(username);
        if(Objects.isNull(usuario)){
            return null;
        }

        AsesorCartera asesor = asesorCarteraRepository.findByUsuarioId(usuario.getIdUsuario());
        if(Objects.isNull(asesor)){
            return null;
        }
        
        List<String> sedes = cuentasPorCobrarRepository.sedesByUsuario(asesor.getIdAsesorCartera());
        items.setSedes(sedes);
        
        
       List<String> venciStrings =cuentasPorCobrarRepository.vencimientosByUsuario(asesor.getIdAsesorCartera()); 
        
        items.setVencimientos(venciStrings);
        
        
        List<String> clasificacionesString =  cuentasPorCobrarRepository.clasificacionJuridicaByUsuario(asesor.getIdAsesorCartera());
     
         items.setClasificacionJuridica(clasificacionesString);
        return items;
    }

}

