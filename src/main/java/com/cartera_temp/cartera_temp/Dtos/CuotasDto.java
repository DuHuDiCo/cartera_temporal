package com.cartera_temp.cartera_temp.Dtos;

public class CuotasDto {

    private int numeroCuota;
    private String fechaVencimiento;
    private double valorCuota;
    private double capitalCuota;
    private double honorarios;
    private double saldoCapital;
    private double saldoHonorario;
    private double saldoIntereses;
    private double interesCuota;
    private PagosDto pagosDto;
    private boolean cumplio;

    public CuotasDto() {
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

    public double getInteresCuota() {
        return interesCuota;
    }

    public void setInteresCuota(double interesCuota) {
        this.interesCuota = interesCuota;
    }

    public boolean isCumplio() {
        return cumplio;
    }

    public void setCumplio(boolean cumplio) {
        this.cumplio = cumplio;
    }

    public PagosDto getPagosDto() {
        return pagosDto;
    }

    public void setPagosDto(PagosDto pagosDto) {
        this.pagosDto = pagosDto;
    }

    public double getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(double saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public double getSaldoHonorario() {
        return saldoHonorario;
    }

    public void setSaldoHonorario(double saldoHonorario) {
        this.saldoHonorario = saldoHonorario;
    }

    public double getSaldoIntereses() {
        return saldoIntereses;
    }

    public void setSaldoIntereses(double saldoIntereses) {
        this.saldoIntereses = saldoIntereses;
    }
    
    
    

}
