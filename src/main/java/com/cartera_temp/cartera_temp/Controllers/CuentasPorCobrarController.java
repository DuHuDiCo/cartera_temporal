
package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Dtos.CuentaToCalculateDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/cuentas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CuentasPorCobrarController {
    
    
    private final CuentasPorCobrarService cuentasPorCobrarService;
    private final FileService fileService;

    public CuentasPorCobrarController(CuentasPorCobrarService cuentasPorCobrarService, FileService fileService) {
        this.cuentasPorCobrarService = cuentasPorCobrarService;
        this.fileService = fileService;
    }

    
    
    @PostMapping("/save")
    public ResponseEntity<List<CuentasPorCobrar>> guardarCuentas(@RequestBody MultipartFile file, @RequestParam(name = "delimitante") String delimitante){
        
        List<CuentasPorCobrar> cuentas = cuentasPorCobrarService.processingData(file, delimitante);
        return CollectionUtils.isEmpty(cuentas)?ResponseEntity.badRequest().build():ResponseEntity.ok(cuentas);
    }
    
    @GetMapping("/cuentasCobrar")
    public ResponseEntity<Page<CuentasPorCobrarResponse>> listarCuentasCobrarByObligacion(@RequestParam(name = "username") String username, @RequestParam(defaultValue = "0", name = "page") int page, @RequestParam(defaultValue = "10", name = "size") int size , @RequestParam(defaultValue = "fecha_creacion", name = "fechaCreacion") String  order){
        Page<CuentasPorCobrarResponse> cuentas = cuentasPorCobrarService.listarCuentasCobrarByAsesor(username,  PageRequest.of(page, size));
        return ResponseEntity.ok(cuentas);
    }
    
    @GetMapping("/getCuentaCobrarByNumeroObligacion")
    public ResponseEntity<CuentasPorCobrarResponse>obtenerCuentaCobrarByNumObligacion(@RequestParam(name = "numeroObligacion")String numeroObligacion){
        
        CuentasPorCobrarResponse cpcRes = cuentasPorCobrarService.getCpcByNumeroObligacion(numeroObligacion);
        if(Objects.isNull(cpcRes)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cpcRes);
        
    }
    
    @GetMapping("/getCuentaCobrarByCedula")
    public ResponseEntity<List<CuentasPorCobrarResponse>> obtenerCuentasPorCobrarByNumeroCedula(@RequestParam(name = "cedula")String numeroObligacion){
        
        List<CuentasPorCobrarResponse> cpcRes = cuentasPorCobrarService.getCpcByNumeroObligacionContaining(numeroObligacion);
        return ResponseEntity.ok(cpcRes);
        
    }
    
    @PutMapping("/updateCuentaCobrarToCalculate")
    public ResponseEntity<CuentasPorCobrarResponse> updateCuentaCobrarToCalculate(@RequestBody CuentaToCalculateDto dto){
        CuentasPorCobrarResponse cpc = cuentasPorCobrarService.updateCpcToCalculate(dto);
        if(Objects.isNull(cpc)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cpc);
    }

    
    @GetMapping("/cuentasByDato")
    public ResponseEntity<List<CuentasPorCobrar>> cuentasCobrarByDato(@RequestParam(name = "dato") String dato){
        List<CuentasPorCobrar> cuentas = cuentasPorCobrarService.buscarCuentasByDatos(dato);
        return ResponseEntity.ok(cuentas);
    }
}
