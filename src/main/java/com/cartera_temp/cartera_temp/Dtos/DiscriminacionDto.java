package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;


public class DiscriminacionDto {

    private Date fechaPactada;
    private double valorCapital;
    private double valorIntereses;
    private double valorHonorarios;
    private String observacionDiscriminacion;

    public DiscriminacionDto() {
    }

    public Date getFechaPactada() {
        return fechaPactada;
    }

    public void setFechaPactada(Date fechaPactada) {
        this.fechaPactada = fechaPactada;
    }

    public double getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(double valorCapital) {
        this.valorCapital = valorCapital;
    }

    public double getValorIntereses() {
        return valorIntereses;
    }

    public void setValorIntereses(double valorIntereses) {
        this.valorIntereses = valorIntereses;
    }

    public double getValorHonorarios() {
        return valorHonorarios;
    }

    public void setValorHonorarios(double valorHonorarios) {
        this.valorHonorarios = valorHonorarios;
    }

    public String getObservacionDiscriminacion() {
        return observacionDiscriminacion;
    }

    public void setObservacionDiscriminacion(String observacionDiscriminacion) {
        this.observacionDiscriminacion = observacionDiscriminacion;
    }
    
    
    
}
