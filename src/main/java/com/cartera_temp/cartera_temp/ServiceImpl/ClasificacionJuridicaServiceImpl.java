package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import com.cartera_temp.cartera_temp.Service.ClasificacionJuridicaService;
import com.cartera_temp.cartera_temp.repository.ClasificacionJuridicaRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service
public class ClasificacionJuridicaServiceImpl implements ClasificacionJuridicaService{
    
    private final ClasificacionJuridicaRepository cjR;

    public ClasificacionJuridicaServiceImpl(ClasificacionJuridicaRepository cjR) {
        this.cjR = cjR;
    }

    @Override
    public ClasificacionJuridica saveClasificacion(ClasificacionJuridica cj) {
        
        if(cj.getClasificacionJuridica() == "" || cj.getClasificacionJuridica() == null){
            return null;
        }
        
        ClasificacionJuridica cjFind = cjR.findByClasificacionJuridica(cj.getClasificacionJuridica());
        if(Objects.nonNull(cjFind)){
            return null;
        }
        
        ClasificacionJuridica cjSave = new ClasificacionJuridica();
        cjSave.setClasificacionJuridica(cj.getClasificacionJuridica().toUpperCase());
        cjSave = cjR.save(cjSave);
        return cjSave;
        
    }

    @Override
    public List<ClasificacionJuridica> getAllClasificacion() {
        List<ClasificacionJuridica> cj = cjR.findAll();
        return cj;
    }

    @Override
    public ClasificacionJuridica getClasificacionById(Long id) {
        
        if(id == 0 || id == null){
            return null;
        }
        
        ClasificacionJuridica cj = cjR.findById(id).orElse(null);
        if(Objects.isNull(cj)){
            return null;
        }
        
        return cj;
        
    }

    @Override
    public ClasificacionJuridica getClasificacionByNombre(String nombre) {
        
        if(nombre == "" || nombre == null){
            return null;
        }
        
        ClasificacionJuridica cj = cjR.findByClasificacionJuridica(nombre);
        if(Objects.isNull(cj)){
            return null;
        }
        
        return cj;
        
    }

    @Override
    public ClasificacionJuridica updateClasificacion(ClasificacionJuridica cj) {
        
        if(cj.getIdClasificacionJuridica() == 0 || cj.getIdClasificacionJuridica() == null || cj.getClasificacionJuridica() == "" || cj.getClasificacionJuridica() == null){
            return null;
        }
        
        ClasificacionJuridica cjUpdate = cjR.findById(cj.getIdClasificacionJuridica()).orElse(null);
        if(Objects.nonNull(cjUpdate)){
            cjUpdate.setClasificacionJuridica(cj.getClasificacionJuridica().toUpperCase());
            cjUpdate = cjR.save(cjUpdate);
            return cjUpdate;
        }
        
        return null;
        
    }
    
}
