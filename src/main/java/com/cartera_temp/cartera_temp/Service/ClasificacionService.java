package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.Clasificacion;
import java.util.List;

public interface ClasificacionService {

    public Clasificacion saveClasificacion(String clasificacion);
    
    public List<Clasificacion> findAllClasificacion();
    
    public Clasificacion updateClasificacion(Long idClasificacion, String newClasificacion);
    
}
