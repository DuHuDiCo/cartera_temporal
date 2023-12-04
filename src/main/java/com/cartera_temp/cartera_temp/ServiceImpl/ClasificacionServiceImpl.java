package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionDto;
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
    public Clasificacion saveClasificacion(Clasificacion clasificacion) {
        
        if(clasificacion.getTipoClasificacion().equals(null)||clasificacion.getTipoClasificacion().equals("")){
            return null;
        }
        
        Clasificacion newClasi = clasificacionRepository.findByTipoClasificacion(clasificacion.getTipoClasificacion());
        
        if(Objects.isNull(newClasi)){
            Clasificacion clasiToSave = new Clasificacion();
            clasiToSave.setTipoClasificacion(clasificacion.getTipoClasificacion());
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
    public Clasificacion updateClasificacion(ClasificacionDto dto) {
        
        if(dto.getIdClasificacion() == null || dto.getIdClasificacion() == 0 || dto.getNewClasificacion().equals("")){
            return null;
        }
        
        Clasificacion clasificacionObtenida = clasificacionRepository.findById(dto.getIdClasificacion()).orElse(null);
        if(Objects.isNull(clasificacionObtenida)){
            return null;
        }
        clasificacionObtenida.setTipoClasificacion(dto.getNewClasificacion());
        clasificacionObtenida = clasificacionRepository.save(clasificacionObtenida);
        return clasificacionObtenida;
        
    }

    @Override
    public Clasificacion getClasificacionById(Long idClasificacion) {
        
        if(idClasificacion.equals(null)||idClasificacion == 0){
            return null;
        }
        
        Clasificacion clasificacion = clasificacionRepository.findById(idClasificacion).orElse(null);
        if(Objects.isNull(clasificacion)){
            return null;
        }
        
        return clasificacion;
        
    }
    
}
