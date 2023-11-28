package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.Clasificacion;
import com.cartera_temp.cartera_temp.Service.ClasificacionService;
import com.cartera_temp.cartera_temp.repository.ClasificacionRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClasificacionServiceImpl implements ClasificacionService{
    
    @Autowired ClasificacionRepository clasificacionRepository;

    @Override
    public Clasificacion saveClasificacion(String clasificacion) {
        
        if(clasificacion.equals(null)||clasificacion.equals("")){
            return null;
        }
        
        Clasificacion newClasi = clasificacionRepository.findClasificacionByTipoClasificacion(clasificacion);
        
        if(Objects.isNull(newClasi)){
            Clasificacion clasiToSave = new Clasificacion();
            clasiToSave.setTipoClasificacion(clasificacion);
            clasiToSave = clasificacionRepository.save(clasiToSave);
            return clasiToSave;
        }
        
        return null;
        
    }

    @Override
    public List<Clasificacion> findAllClasificacion() {
        
        List<Clasificacion> clasi = clasificacionRepository.findAll();
        return clasi;
        
    }

    @Override
    public Clasificacion updateClasificacion(Long idClasificacion, String newClasificacion) {
        
        if(idClasificacion == null || idClasificacion == 0 || newClasificacion.equals("")||newClasificacion.equals(null)){
            return null;
        }
        
        Clasificacion clasificacionObtenida = clasificacionRepository.findById(idClasificacion).orElse(null);
        if(Objects.isNull(clasificacionObtenida)){
            return null;
        }
        clasificacionObtenida.setTipoClasificacion(newClasificacion);
        clasificacionObtenida = clasificacionRepository.save(clasificacionObtenida);
        return clasificacionObtenida;
        
    }
    
}
