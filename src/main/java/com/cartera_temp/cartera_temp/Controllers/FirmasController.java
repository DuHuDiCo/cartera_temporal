package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.FirmasDto;
import com.cartera_temp.cartera_temp.Models.Firmas;
import com.cartera_temp.cartera_temp.Service.FirmasService;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/firmasController")
public class FirmasController {
    
    private final FirmasService fs;
    

    public FirmasController(FirmasService fs) {
        this.fs = fs;
    }
    
    @PostMapping("/saveFirma")
    public ResponseEntity<Firmas> save (FirmasDto dto){
        Firmas f = fs.guardarfirmas(dto);
        if(Objects.isNull(f)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(f);
    }
    
    @GetMapping("/obtenerTodasFirmas")
    public ResponseEntity<List<Firmas>> findAll(){
        List<Firmas> f = fs.listarFirmas();
        return ResponseEntity.ok(f);
    }
    
    @GetMapping("/obtenerFirmaByUsername")
    public ResponseEntity<Firmas> findByUsername(String username){
        Firmas f = fs.findFirmaByUsername(username);
        if(Objects.isNull(f)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(f);
    }
    
    @GetMapping("/obtenerFirmasById/{id}")
    public ResponseEntity<Firmas> findById(@PathVariable("id") Long id){
        Firmas f = fs.findFirmaById(id);
        if(Objects.isNull(f)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(f);
    }
    
    @DeleteMapping("/DeleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        fs.deleteFirma(id);
    }
    
}
