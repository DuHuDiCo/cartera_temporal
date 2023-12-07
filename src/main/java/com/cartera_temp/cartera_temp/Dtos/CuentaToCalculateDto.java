package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class CuentaToCalculateDto {

    private String numeroObligacion;
    
    private double valorTotal;
    
    private double moraObligatoria;
    
    private Date fechaVencimiento;
    
    private String username;

    public CuentaToCalculateDto() {
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getMoraObligatoria() {
        return moraObligatoria;
    }

    public void setMoraObligatoria(double moraObligatoria) {
        this.moraObligatoria = moraObligatoria;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
