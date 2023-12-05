package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionToUpdateDto;
import com.cartera_temp.cartera_temp.Models.ClasificacionTarea;
import com.cartera_temp.cartera_temp.Service.ClasificacionTareaService;
import com.cartera_temp.cartera_temp.repository.ClasificacionTareaRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service
public class ClasificacionTareaServiceImpl implements ClasificacionTareaService{
    
    private final ClasificacionTareaRepository clasificacionTareaRepository;

    public ClasificacionTareaServiceImpl(ClasificacionTareaRepository clasificacionTareaRepository) {
        this.clasificacionTareaRepository = clasificacionTareaRepository;
    }
    
    @Override
    public ClasificacionTarea guardarClasificacionTarea(String clasificacionTarea) {
        
        if(clasificacionTarea == "" || clasificacionTarea == null){
            return null;
        }
        
        
        ClasificacionTarea clasiTarea = clasificacionTareaRepository.findByClasificacionTarea(clasificacionTarea);
        if(Objects.nonNull(clasiTarea)){
            return null;
        }
        
        clasiTarea = new ClasificacionTarea();
        
        clasiTarea.setClasificacionTarea(clasificacionTarea);
        clasiTarea = clasificacionTareaRepository.save(clasiTarea);
        return clasiTarea;
        
    }

    @Override
    public List<ClasificacionTarea> listarClasificacionTarea() {
        
        List<ClasificacionTarea> clasi = clasificacionTareaRepository.findAll();
        return clasi;
        
    }

    @Override
    public ClasificacionTarea obtenerClasificacionTareaByNombre(String clasificacionTarea) {
        
        if(clasificacionTarea == "" || clasificacionTarea == null){
            return null;
        }
        
        ClasificacionTarea clasi = clasificacionTareaRepository.findByClasificacionTarea(clasificacionTarea);
        if(Objects.isNull(clasi)){
            return null;
        }
        return clasi;
    }

    @Override
    public ClasificacionTarea actualizarClasificacionTarea(ClasificacionToUpdateDto dto) {
        
        if(dto.getClasificacionToUpdate() == ""|| dto.getClasificacionToUpdate() == null || dto.getIdClasificacion() == 0 || dto.getIdClasificacion() == null){
            return null;
        }
        
        ClasificacionTarea clasi = clasificacionTareaRepository.findById(dto.getIdClasificacion()).orElse(null);
        if(Objects.isNull(clasi)){
            return null;
        }
        clasi.setClasificacionTarea(dto.getClasificacionToUpdate());
        clasi = clasificacionTareaRepository.save(clasi);
        return clasi;
        
    }

    @Override
    public void eliminarClasificacionTarea(Long idClasificacionTarea) {
        clasificacionTareaRepository.deleteById(idClasificacionTarea);
    }

    @Override
    public ClasificacionTarea obtenerClasificacionTareaById(Long idClasificacionTarea) {
        
        if(idClasificacionTarea == null || idClasificacionTarea == 0){
            return null;
        }
        
        ClasificacionTarea clasi = clasificacionTareaRepository.findById(idClasificacionTarea).orElse(null);
        if(Objects.isNull(clasi)){
            return null;
        }
        return clasi;
    }
    
}
