
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.AsesorCarteraResponse;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AsesorCarteraServiceImpl implements AsesorCarteraService{
    
    
    private final AsesorCarteraRepository asesorCarteraRepository;
    private final usuario_client usuClient;
    private final HttpServletRequest request;

    public AsesorCarteraServiceImpl(AsesorCarteraRepository asesorCarteraRepository, usuario_client usuClient, HttpServletRequest request) {
        this.asesorCarteraRepository = asesorCarteraRepository;
        this.usuClient = usuClient;
        this.request = request;
    }

    @Override
    public AsesorCartera guardarAsesor(Long asesor) {
        AsesorCartera asesorCartera = asesorCarteraRepository.findByUsuarioId(asesor);
        if(Objects.nonNull(asesorCartera)){
            return asesorCartera;
        }
        asesorCartera = new AsesorCartera();
        asesorCartera.setUsuarioId(asesor);
        
        return asesorCarteraRepository.save(asesorCartera);
    }

    @Override
    public List<AsesorCartera> listarAsesores() {
        List<AsesorCartera> asesor = asesorCarteraRepository.findAll();
        AsesorCarteraResponse response = new AsesorCarteraResponse();
        String token = request.getAttribute("token").toString();
        for (AsesorCartera asesorCartera : asesor) {
            
            Usuario usu = usuClient.getUsuarioById(asesorCartera.getIdAsesorCartera(), token);
            
        }
        return asesor;
    }

    @Override
    public AsesorCartera findAsesor(Long asesor) {
        AsesorCartera asesorCartera = asesorCarteraRepository.findByUsuarioId(asesor);
        if(Objects.isNull(asesorCartera)){
            return null;
        }
        return asesorCartera;
    }

   
}
