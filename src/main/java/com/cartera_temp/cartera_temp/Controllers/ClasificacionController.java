package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Models.Clasificacion;
import com.cartera_temp.cartera_temp.Service.ClasificacionService;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Clasificacion> guardarClasificacion(@RequestParam("tipo_clasificacion") String tipoClasificacion){
     
        Clasificacion clasificacion = clasificacionService.saveClasificacion(tipoClasificacion);
        if(Objects.isNull(clasificacion)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasificacion);
        
    }
    
    @GetMapping("/getClasificaciones")
    public ResponseEntity<List<Clasificacion>> obtenerTodasClasificaciones(){
        List<Clasificacion> clasificacions = clasificacionService.findAllClasificacion();
        return ResponseEntity.ok(clasificacions);
    }
    
    @PutMapping("/updateClasificacion")
    public ResponseEntity<Clasificacion> actualizarClasificacion(@RequestParam("id_clasificacion") Long idClasificacion, @RequestParam("new_clasificacion") String newClasificacion){
        Clasificacion clasificacion = clasificacionService.updateClasificacion(idClasificacion, newClasificacion);
        if(Objects.isNull(clasificacion)){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasificacion);
    }
    
}
