package com.cartera_temp.cartera_temp.Components;

import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import java.io.IOException;
import java.util.List;

public interface GenerarPdf {

    public String generarReporteAcuerdoPagoToClient(CuentasPorCobrar cpc) throws IOException, ClassNotFoundException;
    
    public String generarReportePagoCuotas(CuentasPorCobrar cpc)throws IOException, ClassNotFoundException;
    
}
