package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.NombreClasificacionDto;
import com.cartera_temp.cartera_temp.Models.NombresClasificacion;
import com.cartera_temp.cartera_temp.Service.NombreClasificacionService;
import com.cartera_temp.cartera_temp.repository.NombresClasificacionRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class NombreClasificacionServiceImpl implements NombreClasificacionService{
    
    private final NombresClasificacionRepository nombresClasificacionRepository;

    public NombreClasificacionServiceImpl(NombresClasificacionRepository nombresClasificacionRepository) {
        this.nombresClasificacionRepository = nombresClasificacionRepository;
    }

    @Override
    public NombresClasificacion guardarNombresClasificacion(NombreClasificacionDto dto) {
        
        if(dto.getNombreClasificacion() == "" || dto.getNombreClasificacion() == null){
            return null;
        }
        
        NombresClasificacion nc = nombresClasificacionRepository.findByNombre(dto.getNombreClasificacion());
        
        if(!Objects.isNull(nc)){
            return null;
        }
        
        NombresClasificacion ncToSave = new NombresClasificacion();
        
        ncToSave.setNombre(dto.getNombreClasificacion());
        ncToSave = nombresClasificacionRepository.save(ncToSave);
        
        return ncToSave;
        
    }

    @Override
    public NombresClasificacion obtenerNombresClasificacionByNombre(String nombre) {
        
        if(nombre == null || nombre == ""){
            return null;
        }
        
        NombresClasificacion nc = nombresClasificacionRepository.findByNombre(nombre);
        if(Objects.isNull(nc)){
            return null;
        }
        
        return nc;
        
    }

    @Override
    public NombresClasificacion obtenerNombresClasificacionById(Long id) {
        
        if(id == null || id == 0){
            return null;
        }
        
        NombresClasificacion nc = nombresClasificacionRepository.findById(id).orElse(null);
        if(Objects.isNull(nc)){
            return null;
        }
        return nc;
        
    }

    @Override
    public List<NombresClasificacion> obtenerTodosNombresClasificacion() {
        
        List<NombresClasificacion> ncList = nombresClasificacionRepository.findAll();
        
        if(Objects.isNull(ncList)){
            return null;
        }
        
        return ncList;
        
    }

    @Override
    public void eliminarNombreClasificacion(Long id) {
        nombresClasificacionRepository.deleteById(id);
    }
    
}
