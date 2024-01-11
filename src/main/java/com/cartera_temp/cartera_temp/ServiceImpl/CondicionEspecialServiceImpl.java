package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.CondicionEspecial;
import com.cartera_temp.cartera_temp.Service.CondicionEspecialService;
import com.cartera_temp.cartera_temp.repository.CondicionEspecialRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class CondicionEspecialServiceImpl implements CondicionEspecialService{
    
    private final CondicionEspecialRepository condicionEspecialRepository;

    public CondicionEspecialServiceImpl(CondicionEspecialRepository condicionEspecialRepository) {
        this.condicionEspecialRepository = condicionEspecialRepository;
    }

    @Override
    public CondicionEspecial saveCondicion(CondicionEspecial ce) {
        
        if(ce.getCondicionEspecial() == null || ce.getCondicionEspecial() == ""){
            return null;
        }
        
        CondicionEspecial ceFind = condicionEspecialRepository.findByCondicionEspecial(ce.getCondicionEspecial());
        if(Objects.nonNull(ceFind)){
            return null;
        }
        
        CondicionEspecial ceSave = new CondicionEspecial();
        
        ceSave.setCondicionEspecial(ce.getCondicionEspecial().toUpperCase());
        
        ceSave = condicionEspecialRepository.save(ceSave);
        return ceSave;
        
    }

    @Override
    public List<CondicionEspecial> getAllCondicion() {
        List<CondicionEspecial> ce = condicionEspecialRepository.findAll();
        return ce;
    }

    @Override
    public CondicionEspecial getCondicionById(Long id) {
        
        if(id == 0 || id == null){
            return null;
        }
        
        CondicionEspecial ce = condicionEspecialRepository.findById(id).orElse(null);
        if(Objects.isNull(ce)){
            return null;
        }
        return ce;
        
    }

    @Override
    public CondicionEspecial getCondicionByNombre(String nombre) {
        
        if(nombre == "" || nombre == null){
            return null;
        }
        
        CondicionEspecial ce = condicionEspecialRepository.findByCondicionEspecial(nombre);
        if(Objects.isNull(ce)){
            return null;
        }
        return ce;
        
    }

    @Override
    public CondicionEspecial updateCondicion(CondicionEspecial ce) {
        
        if(ce.getIdCondicionEspecial() == null || ce.getIdCondicionEspecial() == 0 || ce.getCondicionEspecial() == "" || ce.getCondicionEspecial() == null){
            return null;
        }
        
        CondicionEspecial ceUpdate = condicionEspecialRepository.findById(ce.getIdCondicionEspecial()).orElse(null);
        if(Objects.nonNull(ceUpdate)){
            ceUpdate.setCondicionEspecial(ce.getCondicionEspecial().toUpperCase());
            ceUpdate = condicionEspecialRepository.save(ceUpdate);
            return ceUpdate;
        }
        
        return null;
        
    }
    
    
    
}
