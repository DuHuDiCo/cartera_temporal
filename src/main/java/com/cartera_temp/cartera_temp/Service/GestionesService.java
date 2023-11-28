package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.List;

public interface GestionesService {
 
    public Gestiones saveOneGestion(GestionesDto dto, String numeroObligacion);
    
    public List<Gestiones> saveMultipleGestiones(List<GestionesDto> dto);
    
    public List<Gestiones> findHistoricoGestiones(String numeroObligacion);
    
}
