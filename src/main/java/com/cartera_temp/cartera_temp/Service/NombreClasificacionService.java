package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.NombreClasificacionDto;
import com.cartera_temp.cartera_temp.Models.NombresClasificacion;
import java.util.List;

public interface NombreClasificacionService {

    NombresClasificacion guardarNombresClasificacion(NombreClasificacionDto dto);
    
    NombresClasificacion obtenerNombresClasificacionByNombre(String nombre);
    
    NombresClasificacion obtenerNombresClasificacionById(Long id);
    
    List<NombresClasificacion> obtenerTodosNombresClasificacion();
    
    void eliminarNombreClasificacion(Long id);
    
}
