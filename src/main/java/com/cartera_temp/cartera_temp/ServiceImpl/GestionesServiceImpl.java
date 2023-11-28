package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionesServiceImpl implements GestionesService{
    
    @Autowired GestionesRepository gestionesRepository;

    @Override
    public Gestiones saveOneGestion(GestionesDto dto, String numeroObligacion) {
        
        if(numeroObligacion.equals(null)||numeroObligacion.equals("")){
            return null;
        }
        
    }

    @Override
    public List<Gestiones> saveMultipleGestiones(List<GestionesDto> dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Gestiones> findHistoricoGestiones(String numeroObligacion) {
        
        if(numeroObligacion == "" || numeroObligacion == null){
            return null;
        }
        
        List<Gestiones> gestion = gestionesRepository.findGestionesByNumeroObligacion(numeroObligacion);
        
        if(Objects.isNull(gestion)){
            return null;
        }
        
        return gestion;
        
    }
    
}
