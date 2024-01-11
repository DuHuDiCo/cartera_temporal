
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.AsesorCarteraResponse;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import java.util.ArrayList;
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
    public List<AsesorCarteraResponse> listarAsesores() {
        List<AsesorCartera> asesor = asesorCarteraRepository.findAll();
        String token = request.getAttribute("token").toString();
        List<AsesorCarteraResponse> listRes = new ArrayList<>();
        for (AsesorCartera asesorCartera : asesor) {
            AsesorCarteraResponse response = new AsesorCarteraResponse();
            Usuario usu = usuClient.getUsuarioById(asesorCartera.getIdAsesorCartera(), token);
            response.setUsuario(usu);
            listRes.add(response);
        }
        return listRes;
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
