package com.cartera_temp.cartera_temp.Service;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Dtos.AlertsGestiones;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Dtos.LinkDto;
import com.cartera_temp.cartera_temp.Dtos.LinkToClient;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.List;

public interface GestionesService {
 
    public GestionResponse saveOneGestion(GestionToSaveDto dto);
    
    public List<Gestiones> saveMultipleGestiones(GestionesDataDto dataDto);
    
    public List<GestionResponse> findHistoricoGestiones(String numeroObligacion);
    
    public List<Gestiones> guardarGestiones(List<GestionesDto> gestiones);
    
    public String sendLastDatoAdicional(String numeroObligacion);
    
    public LinkToClient sendLinkAndPdfToClient(LinkDto dto);
    
    public void desactivateAcuerdoPago(Long idAcuerdoPago);
    
    public List<Cuotas>  cuotaCumplio(List<Long> idCuota);
    
    public AlertsGestiones alertasDeGestiones(String username, String fecha);
    
}
