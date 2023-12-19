package com.cartera_temp.cartera_temp.Controllers;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.LinkDto;
import com.cartera_temp.cartera_temp.Dtos.LinkToClient;
import com.cartera_temp.cartera_temp.Dtos.MessageResponse;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/gestiones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GestionesController {

    private final GestionesService gestionesService;
    
    public GestionesController(com.cartera_temp.cartera_temp.Service.GestionesService gestionesService) {
        this.gestionesService = gestionesService;
    }
    
    @PostMapping("/saveOneGestion")
    public ResponseEntity<GestionResponse> saveOneGestion(@RequestBody GestionToSaveDto  dto){
        GestionResponse gestion = gestionesService.saveOneGestion(dto);
        if(Objects.isNull(gestion)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gestion);
    }
    
    @GetMapping("/getGestionByNumObligacion/{num_obligacion}")
    public ResponseEntity<List<GestionResponse>> getGestionesByNumObligacion(@PathVariable("num_obligacion") String numeroObligacion){
        List<GestionResponse> gestion = gestionesService.findHistoricoGestiones(numeroObligacion);
        return ResponseEntity.ok(gestion);
    }
    
    
    @PostMapping("/saveGestiones")
    public ResponseEntity<List<Gestiones>> guardarGestiones(@RequestBody GestionesDataDto dataDto){
       List<Gestiones> gestiones = gestionesService.saveMultipleGestiones(dataDto);
        
        return ResponseEntity.ok(gestiones);
    }
    
    @GetMapping("/getLastDatoAdicionalGestion/{num_obligacion}")
    public ResponseEntity<MessageResponse> getLastDatoAdicional(@PathVariable("num_obligacion")String numeroObligacion){
        
        String datoAdicional = gestionesService.sendLastDatoAdicional(numeroObligacion);
        return ResponseEntity.ok(new MessageResponse(datoAdicional));
        
    }
    
    @PutMapping("/desactivateAcuerdoPago/{id}")
    public void desactivateAcuerpoPago(@PathVariable("id")Long idAcuerdoPago){
        gestionesService.desactivateAcuerdoPago(idAcuerdoPago);
    }
    
    @PutMapping("/linkAndReporteAcuerdoToClient")
    public ResponseEntity<LinkToClient> link (@RequestBody LinkDto dto){
        LinkToClient linkClient = gestionesService.sendLinkAndPdfToClient(dto);
        if(Objects.isNull(linkClient)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(linkClient);
    }
    
}
