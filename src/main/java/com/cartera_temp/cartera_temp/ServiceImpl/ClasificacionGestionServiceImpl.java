package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionDto;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.TipoClasificacionGestion;
import com.cartera_temp.cartera_temp.Service.ClasificacionService;
import com.cartera_temp.cartera_temp.repository.ClasificacionGestionRepository;
import com.cartera_temp.cartera_temp.repository.TipoClasificacionGestionRepository;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClasificacionGestionServiceImpl implements ClasificacionService{
    
    @Autowired TipoClasificacionGestionRepository clasificacionRepository;

    @Override
    public TipoClasificacionGestion saveClasificacion(TipoClasificacionGestion clasificacion) {
        
        if(Objects.isNull(clasificacion.getClasificacion())||clasificacion.getClasificacion().equals("")){
            return null;
        }
        
        TipoClasificacionGestion newClasi = clasificacionRepository.findByClasificacion(clasificacion.getClasificacion());
        
        if(Objects.isNull(newClasi)){
            TipoClasificacionGestion clasiToSave = new TipoClasificacionGestion();
            clasiToSave.setClasificacion(clasificacion.getClasificacion());
            clasiToSave = clasificacionRepository.save(clasiToSave);
            return clasiToSave;
        }
        
        return null;
        
    }

    @Override
    public List<TipoClasificacionGestion> findAllClasificacion() {
        
        List<TipoClasificacionGestion> clasi = clasificacionRepository.findAll();
        return clasi;
        
    }

    @Override
    public TipoClasificacionGestion updateClasificacion(ClasificacionDto dto) {
        
        if(dto.getIdClasificacion() == null || dto.getIdClasificacion() == 0 || dto.getNewClasificacion().equals("")){
            return null;
        }
        
        TipoClasificacionGestion clasificacionObtenida = clasificacionRepository.findById(dto.getIdClasificacion()).orElse(null);
        if(Objects.isNull(clasificacionObtenida)){
            return null;
        }
        clasificacionObtenida.setClasificacion(dto.getNewClasificacion());
        clasificacionObtenida = clasificacionRepository.save(clasificacionObtenida);
        return clasificacionObtenida;
        
    }

    @Override
    public TipoClasificacionGestion getClasificacionById(Long idClasificacion) {
        
        if(Objects.isNull(idClasificacion)||idClasificacion == 0){
            return null;
        }
        
        TipoClasificacionGestion clasificacion = clasificacionRepository.findById(idClasificacion).orElse(null);
        if(Objects.isNull(clasificacion)){
            return null;
        }
        
        return clasificacion;
        
    }
    
}
