package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.Nota;
import com.cartera_temp.cartera_temp.Models.Tarea;

public class ClasificacionDtoTipo {

    private String tipoClasificacion;
    private Tarea tarea;
    private Nota nota;
    private AcuerdoPagoDto acuerdoPago;

    public ClasificacionDtoTipo() {
    }

    public String getTipoClasificacion() {
        return tipoClasificacion;
    }

    public void setTipoClasificacion(String tipoClasificacion) {
        this.tipoClasificacion = tipoClasificacion;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public AcuerdoPagoDto getAcuerdoPago() {
        return acuerdoPago;
    }

    public void setAcuerdoPago(AcuerdoPagoDto acuerdoPago) {
        this.acuerdoPago = acuerdoPago;
    }

    
}
