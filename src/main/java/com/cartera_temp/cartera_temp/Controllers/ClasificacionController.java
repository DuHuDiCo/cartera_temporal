package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionDto;

import com.cartera_temp.cartera_temp.Models.TipoClasificacionGestion;
import com.cartera_temp.cartera_temp.Service.ClasificacionService;
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
@RequestMapping("/api/v1/clasificacion")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClasificacionController {

    
    
    private final ClasificacionService clasificacionService;
    
    public ClasificacionController(com.cartera_temp.cartera_temp.Service.ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
    }
    
    @PostMapping("/saveClasificacion")
    public ResponseEntity<TipoClasificacionGestion> guardarClasificacion(@RequestBody TipoClasificacionGestion clasificacionBody){
     
        TipoClasificacionGestion clasificacion = clasificacionService.saveClasificacion(clasificacionBody);
        if(Objects.isNull(clasificacion)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasificacion);
        
    }
    
    @GetMapping("/getClasificaciones")
    public ResponseEntity<List<TipoClasificacionGestion>> obtenerTodasClasificaciones(){
        List<TipoClasificacionGestion> clasificacions = clasificacionService.findAllClasificacion();
        return ResponseEntity.ok(clasificacions);
    }
    
    @GetMapping("/getClasificacionById/{id_clasificacion}")
    public ResponseEntity<TipoClasificacionGestion> obtenerClasificacionById(@PathVariable("id_clasificacion") Long idClasificacion){
        TipoClasificacionGestion clasificacion = clasificacionService.getClasificacionById(idClasificacion);
        if(Objects.isNull(clasificacion)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasificacion);
    }
    
    @PutMapping("/updateClasificacion")
    public ResponseEntity<TipoClasificacionGestion> actualizarClasificacion(@RequestBody ClasificacionDto dto){
        TipoClasificacionGestion clasificacion = clasificacionService.updateClasificacion(dto);
        if(Objects.isNull(clasificacion)){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasificacion);
    }
    
}
