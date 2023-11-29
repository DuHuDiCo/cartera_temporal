package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/gestiones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GestionesController {

    private final GestionesService gestionesService;
    
    public GestionesController(com.cartera_temp.cartera_temp.Service.GestionesService gestionesService) {
        this.gestionesService = gestionesService;
    }
    
    @PostMapping("/saveOneGestion")
    public ResponseEntity<GestionResponse> saveOneGestion(@RequestBody GestionesDto dto){
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
    public ResponseEntity<List<Gestiones>> guardarGestiones(@RequestBody MultipartFile file, @RequestParam(name = "delimitante") String delimitante){
       List<Gestiones> gestiones = gestionesService.saveMultipleGestiones(file, delimitante);
        
        return ResponseEntity.ok(gestiones);
    }
    
}
