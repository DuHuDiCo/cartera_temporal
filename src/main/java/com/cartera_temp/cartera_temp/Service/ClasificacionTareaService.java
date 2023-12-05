package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionToUpdateDto;
import com.cartera_temp.cartera_temp.Models.ClasificacionTarea;
import java.util.List;

public interface ClasificacionTareaService {

    public ClasificacionTarea guardarClasificacionTarea(String clasificacionTarea);

    public List<ClasificacionTarea> listarClasificacionTarea();

    public ClasificacionTarea obtenerClasificacionTareaByNombre(String clasificacionTarea);

    public ClasificacionTarea obtenerClasificacionTareaById(Long idClasificacionTarea);

    public ClasificacionTarea actualizarClasificacionTarea(ClasificacionToUpdateDto dto);

    public String eliminarClasificacionTarea(Long idClasificacionTarea);

}
