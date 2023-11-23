
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class AsesorCarteraServiceImpl implements AsesorCarteraService{
    
    
    private final AsesorCarteraRepository asesorCarteraRepository;

    public AsesorCarteraServiceImpl(AsesorCarteraRepository asesorCarteraRepository) {
        this.asesorCarteraRepository = asesorCarteraRepository;
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
