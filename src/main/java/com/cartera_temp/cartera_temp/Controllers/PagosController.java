package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasResponse;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Service.PagosService;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PagosController {
    private final PagosService ps;

    public PagosController(PagosService ps) {
        this.ps = ps;
    }
    
    @PostMapping("/guardarPago")
    public ResponseEntity<PagosCuotasResponse> guardarPago (@RequestBody PagosCuotasDto dto){
        PagosCuotasResponse acu = ps.guardarPago(dto);
        if(Objects.isNull(acu)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(acu);
    }
    
}
