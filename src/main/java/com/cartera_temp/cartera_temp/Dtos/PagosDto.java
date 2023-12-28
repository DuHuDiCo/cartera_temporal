package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class PagosDto {

    private double valorPago;
    private Date fechaPago;
    private double saldoCuota;

    public PagosDto() {
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getSaldoCuota() {
        return saldoCuota;
    }

    public void setSaldoCuota(double saldoCuota) {
        this.saldoCuota = saldoCuota;
    }
}
