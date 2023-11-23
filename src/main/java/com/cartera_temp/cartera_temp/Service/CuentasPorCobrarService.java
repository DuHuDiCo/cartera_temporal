

package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import java.util.List;

public interface CuentasPorCobrarService {
    
    public List<CuentasPorCobrar> guardarCuentas(List<CuentasPorCobrarDto> cuentaPorCobrar);
    
    public List<CuentasPorCobrarResponse> listarCuentasCobrarByAsesor(String username);

}
