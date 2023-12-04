package com.cartera_temp.cartera_temp.Dtos;

public class ClasificacionDtoTipo {

    private String tipoClasificacion;
    private TareaDto tarea;
    private NotaDto nota;
    private AcuerdoPagoDto acuerdoPago;

    public ClasificacionDtoTipo() {
    }

    public String getTipoClasificacion() {
        return tipoClasificacion;
    }

    public void setTipoClasificacion(String tipoClasificacion) {
        this.tipoClasificacion = tipoClasificacion;
    }

    public TareaDto getTarea() {
        return tarea;
    }

    public void setTarea(TareaDto tarea) {
        this.tarea = tarea;
    }

    public NotaDto getNota() {
        return nota;
    }

    public void setNota(NotaDto nota) {
        this.nota = nota;
    }

    public AcuerdoPagoDto getAcuerdoPago() {
        return acuerdoPago;
    }

    public void setAcuerdoPago(AcuerdoPagoDto acuerdoPago) {
        this.acuerdoPago = acuerdoPago;
    }

    
}
