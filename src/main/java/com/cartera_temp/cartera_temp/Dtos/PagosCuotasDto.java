package com.cartera_temp.cartera_temp.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PagosCuotasDto {

    private String numeroObligacion;
    private String numeroRecibo;
    private double saldo;
    private List<CuotasDto> cuotasDto = new ArrayList<>();
    private double valorTotal;
    private String metodoPago;
    private String detalle;
    private String username;

    public PagosCuotasDto() {
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public List<CuotasDto> getCuotasDto() {
        return cuotasDto;
    }

    public void setCuotasDto(List<CuotasDto> cuotasDto) {
        this.cuotasDto = cuotasDto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
