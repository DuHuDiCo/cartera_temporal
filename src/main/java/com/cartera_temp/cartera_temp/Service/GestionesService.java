package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface GestionesService {
 
    public Gestiones saveOneGestion(GestionesDto dto);
    
    public List<Gestiones> saveMultipleGestiones(MultipartFile file, String delimitante);
    
    public List<Gestiones> findHistoricoGestiones(String numeroObligacion);
    
    public List<Gestiones> guardarGestiones(List<GestionesDto> gestiones);
    
    
}
