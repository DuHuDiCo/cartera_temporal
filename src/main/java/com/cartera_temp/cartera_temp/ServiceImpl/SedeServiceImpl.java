
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.Sede;
import com.cartera_temp.cartera_temp.Service.SedeService;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public class SedeServiceImpl implements SedeService{

    private final SedeRepository sedeRepository;

    public SedeServiceImpl(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }
    
    
    
    @Override
    public Sede guardarSede(Sede sede) {
        Sede findSede = sedeRepository.findBySede(sede.getSede());
        if(Objects.nonNull(findSede)){
            return findSede;
        }
        findSede = new Sede();
        findSede.setSede(sede.getSede().toUpperCase());
        findSede.setDireccionSede(sede.getDireccionSede().toUpperCase());
        findSede.setNombreComercialSede(sede.getNombreComercialSede().toUpperCase());
        findSede.setTelefonoSede(sede.getTelefonoSede().toUpperCase());
        
        return sedeRepository.save(findSede);
    }

    @Override
    public List<Sede> listarSede() {
        List<Sede> sedes = sedeRepository.findAll();
        return sedes;
    }

    @Override
    public Sede findSede(String sede) {
        Sede sed = sedeRepository.findBySede(sede);
        if(Objects.isNull(sed)){
            return null;
        }
        return sed;
    }

}
