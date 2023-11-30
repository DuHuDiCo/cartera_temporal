package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class DiscriminacionResponse {

    private String numeroObligacion;
    private String nombreCliente;
    private Date fechaCreacionGestion;
    private Date fechaAcuerdo;
    private Double discriminacionValorCapital;
    private Double discriminacionValorIntereses;
    private Double discriminacionValorHonorarios;
    private String observacionDiscriminacion;
    private String tipoDiscriminante;
    private String asesorCartera;

    public DiscriminacionResponse() {
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaCreacionGestion() {
        return fechaCreacionGestion;
    }

    public void setFechaCreacionGestion(Date fechaCreacionGestion) {
        this.fechaCreacionGestion = fechaCreacionGestion;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    public Double getDiscriminacionValorCapital() {
        return discriminacionValorCapital;
    }

    public void setDiscriminacionValorCapital(Double discriminacionValorCapital) {
        this.discriminacionValorCapital = discriminacionValorCapital;
    }

    public Double getDiscriminacionValorIntereses() {
        return discriminacionValorIntereses;
    }

    public void setDiscriminacionValorIntereses(Double discriminacionValorIntereses) {
        this.discriminacionValorIntereses = discriminacionValorIntereses;
    }

    public Double getDiscriminacionValorHonorarios() {
        return discriminacionValorHonorarios;
    }

    public void setDiscriminacionValorHonorarios(Double discriminacionValorHonorarios) {
        this.discriminacionValorHonorarios = discriminacionValorHonorarios;
    }

    public String getObservacionDiscriminacion() {
        return observacionDiscriminacion;
    }

    public void setObservacionDiscriminacion(String observacionDiscriminacion) {
        this.observacionDiscriminacion = observacionDiscriminacion;
    }

    public String getTipoDiscriminante() {
        return tipoDiscriminante;
    }

    public void setTipoDiscriminante(String tipoDiscriminante) {
        this.tipoDiscriminante = tipoDiscriminante;
    }

    public String getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(String asesorCartera) {
        this.asesorCartera = asesorCartera;
    }
    
    

}
