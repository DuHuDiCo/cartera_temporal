
package com.cartera_temp.cartera_temp.Controllers;

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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentasPorCobrarController {
    
    
    private final CuentasPorCobrarService cuentasPorCobrarService;
    private final FileService fileService;

    public CuentasPorCobrarController(CuentasPorCobrarService cuentasPorCobrarService, FileService fileService) {
        this.cuentasPorCobrarService = cuentasPorCobrarService;
        this.fileService = fileService;
    }

    
    
    @PostMapping("/save")
    public ResponseEntity<List<CuentasPorCobrar>> guardarCuentas(@RequestBody MultipartFile file, @RequestParam(name = "delimitante") String delimitante){
        List<CuentasPorCobrarDto> cuentasDto = fileService.readFile(file, delimitante);
        List<CuentasPorCobrar> cuentas = cuentasPorCobrarService.guardarCuentas(cuentasDto);
        return CollectionUtils.isEmpty(cuentas)?ResponseEntity.badRequest().build():ResponseEntity.ok(cuentas);
    }
    
    @PostMapping("/cuentasCobrar")
    public ResponseEntity<List<CuentasPorCobrarResponse>> listarCuentasCobrarByObligacion(@RequestParam(name = "username") String username){
        List<CuentasPorCobrarResponse> cuentas = cuentasPorCobrarService.listarCuentasCobrarByAsesor(username);
        return CollectionUtils.isEmpty(cuentas)?ResponseEntity.badRequest().build():ResponseEntity.ok(cuentas);
    }

}
