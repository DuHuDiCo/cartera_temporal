package com.cartera_temp.cartera_temp.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PagosCuotasDto {

    private String numeroObligacion;
    private String numeroRecibo;
    private int saldo;
    private List<CuotasDto> cuotasDto = new ArrayList<>();
    private int valorTotal;
    private int capitalTotal;
    private int acuerdoTotal;
    private int honorariosTotal;
    private int interesesTotal;
    private String metodoPago;
    private String detalle;
    private boolean cumpliendo;
    private String username;
    private String nombreClasificacion;
    

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

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(int capitalTotal) {
        this.capitalTotal = capitalTotal;
    }

    public int getAcuerdoTotal() {
        return acuerdoTotal;
    }

    public void setAcuerdoTotal(int acuerdoTotal) {
        this.acuerdoTotal = acuerdoTotal;
    }

    public int getHonorariosTotal() {
        return honorariosTotal;
    }

    public void setHonorariosTotal(int honorariosTotal) {
        this.honorariosTotal = honorariosTotal;
    }

    public int getInteresesTotal() {
        return interesesTotal;
    }

    public void setInteresesTotal(int interesesTotal) {
        this.interesesTotal = interesesTotal;
    }

    public boolean isCumpliendo() {
        return cumpliendo;
    }

    public void setCumpliendo(boolean cumpliendo) {
        this.cumpliendo = cumpliendo;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }
    
    
    
}
