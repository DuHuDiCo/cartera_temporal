package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.Pagos;

public interface PagosService {
    
    public AcuerdoPago guardarPago(PagosCuotasDto dto);
    
}
