
package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.CuentaToCalculateDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CuentasPorCobrarService {

    public List<CuentasPorCobrar> guardarCuentas(List<CuentasPorCobrarDto> cuentaPorCobrar);

    public Page<CuentasPorCobrarResponse> listarCuentasCobrarByAsesor(String username, Pageable pageable);

    public List<CuentasPorCobrar> processingData(MultipartFile file, String delimitante);

    public List<CuentasPorCobrarResponse> getCpcByNumeroObligacionContaining(String numeroObligacion);

    public CuentasPorCobrarResponse getCpcByNumeroObligacion(String numeroObligacion);

    public CuentasPorCobrarResponse updateCpcToCalculate(CuentaToCalculateDto dto);

    public List<CuentasPorCobrarResponse> buscarCuentasByDatos(String dato);

    public Page<CuentasPorCobrarResponse> filtrosCpcs(FiltroDto dto, Pageable pageable);

    public Page<CuentasPorCobrarResponse> listarCuentasCobrar(Pageable pageable);

    public ResponseEntity<Object> validarBlocked(Long idCuenta);

    public ResponseEntity<Object> cambiarBlocked(Long idCuenta);

}
