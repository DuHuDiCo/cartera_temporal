package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionDto;

import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.TipoClasificacionGestion;
import java.util.List;

public interface ClasificacionService {

    public TipoClasificacionGestion saveClasificacion(TipoClasificacionGestion clasificacion);
    
    public List<TipoClasificacionGestion> findAllClasificacion();
    
    public TipoClasificacionGestion updateClasificacion(ClasificacionDto dto);
    
    public TipoClasificacionGestion getClasificacionById(Long idClasificacion);
    
}
