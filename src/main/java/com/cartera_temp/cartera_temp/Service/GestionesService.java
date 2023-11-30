package com.cartera_temp.cartera_temp.Service;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface GestionesService {
 
    public GestionResponse saveOneGestion(GestionToSaveDto dto);
    
    public List<GestionesDto> saveMultipleGestiones(GestionesDataDto dataDto);
    
    public List<GestionResponse> findHistoricoGestiones(String numeroObligacion);
    
    public List<Gestiones> guardarGestiones(List<GestionesDto> gestiones);
    
    public String sendLastDatoAdicional(String numeroObligacion);
}
