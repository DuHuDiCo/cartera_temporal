package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.ClasificacionToUpdateDto;
import com.cartera_temp.cartera_temp.Models.ClasificacionTarea;
import com.cartera_temp.cartera_temp.Service.ClasificacionTareaService;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clasificacionTarea")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClasificacionTareaController {
    
    private final ClasificacionTareaService clasificacionTareaService;

    public ClasificacionTareaController(ClasificacionTareaService clasificacionTareaService) {
        this.clasificacionTareaService = clasificacionTareaService;
    }
    
    @PostMapping("/saveClasificacionTarea")
    public ResponseEntity<ClasificacionTarea> saveClasificacionTarea(@RequestBody ClasificacionTarea ct){
        ClasificacionTarea clasi = clasificacionTareaService.guardarClasificacionTarea(ct.getClasificacionTarea());
        if(Objects.isNull(clasi)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasi);
    }
    
    @GetMapping("/getClasificacionesTarea")
    public ResponseEntity<List<ClasificacionTarea>> getAllClasiTarea(){
        List<ClasificacionTarea> ct = clasificacionTareaService.listarClasificacionTarea();
        return ResponseEntity.ok(ct);
    }
    
    @GetMapping("/getClasificacionTareaById/{id}")
    public ResponseEntity<ClasificacionTarea> getClasiTareaById(@PathVariable("id") Long idClasiTarea){
        ClasificacionTarea clasi = clasificacionTareaService.obtenerClasificacionTareaById(idClasiTarea);
        if(Objects.isNull(clasi)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clasi);
    }
    
    @GetMapping("/getClasificacionTareaByNombre")
    public ResponseEntity<ClasificacionTarea> getClasiTareaByNombre(@RequestParam("clasificacionTarea") String clasificacionTarea){
        ClasificacionTarea ct = clasificacionTareaService.obtenerClasificacionTareaByNombre(clasificacionTarea);
        if(Objects.isNull(ct)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ct);
    }
    
    @DeleteMapping("/eliminarClasificacionTarea/{id}")
    public ResponseEntity<String> deleteClasificacionTarea(@PathVariable("id") Long idClasificacionTarea){
        clasificacionTareaService.eliminarClasificacionTarea(idClasificacionTarea);
        return ResponseEntity.ok("ELIMINADO");
    }
    
    @PutMapping("/actualizarClasificacionTarea")
    public ResponseEntity<ClasificacionTarea> updateClasificacionTarea (@RequestBody ClasificacionToUpdateDto dto){
        ClasificacionTarea ct = clasificacionTareaService.actualizarClasificacionTarea(dto);
        if(Objects.isNull(ct)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ct);
    }
}
