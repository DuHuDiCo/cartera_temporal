package com.cartera_temp.cartera_temp.Components;

import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import java.io.IOException;

public interface GenerarPdf {

    public String generarReporteAcuerdoPagoToClient(CuentasPorCobrar cpc, ClientesDto cliente,String username) throws IOException, ClassNotFoundException;
    
    public String generarReportePagoCuotas(PagosCuotasDto dto)throws IOException, ClassNotFoundException;
    
}
