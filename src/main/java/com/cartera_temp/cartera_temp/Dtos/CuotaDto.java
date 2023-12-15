package com.cartera_temp.cartera_temp.Dtos;

public class CuotaDto {

    private int numeroCuota;
    private String fechaVencimiento;
    private double valorCuota;
    private double capitalCuota;
    private double honorarios;
    private boolean cumplio;

    public CuotaDto() {
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getCapitalCuota() {
        return capitalCuota;
    }

    public void setCapitalCuota(double capitalCuota) {
        this.capitalCuota = capitalCuota;
    }

    public double getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(double honorarios) {
        this.honorarios = honorarios;
    }

    public boolean isCumplio() {
        return cumplio;
    }

    public void setCumplio(boolean cumplio) {
        this.cumplio = cumplio;
    }

    
    
}
