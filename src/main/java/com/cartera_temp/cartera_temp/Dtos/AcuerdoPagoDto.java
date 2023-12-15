package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.Cuotas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcuerdoPagoDto {

    private String detalle;
    private double valorCuotaMensual;
    private String tipoAcuerdo;
    private double valorTotalAcuerdo;
    private double valorInteresesMora;
    private double honoriarioAcuerdo;
    private Date fechaCompromiso;
    private List<CuotaDto> cuotasList = new ArrayList<>();
    private String username;

    public AcuerdoPagoDto() {
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getValorCuotaMensual() {
        return valorCuotaMensual;
    }

    public void setValorCuotaMensual(double valorCuotaMensual) {
        this.valorCuotaMensual = valorCuotaMensual;
    }

    public String getTipoAcuerdo() {
        return tipoAcuerdo;
    }

    public void setTipoAcuerdo(String tipoAcuerdo) {
        this.tipoAcuerdo = tipoAcuerdo;
    }

    public double getValorTotalAcuerdo() {
        return valorTotalAcuerdo;
    }

    public void setValorTotalAcuerdo(double valorTotalAcuerdo) {
        this.valorTotalAcuerdo = valorTotalAcuerdo;
    }

    public double getValorInteresesMora() {
        return valorInteresesMora;
    }

    public void setValorInteresesMora(double valorInteresesMora) {
        this.valorInteresesMora = valorInteresesMora;
    }

    public double getHonoriarioAcuerdo() {
        return honoriarioAcuerdo;
    }

    public void setHonoriarioAcuerdo(double honoriarioAcuerdo) {
        this.honoriarioAcuerdo = honoriarioAcuerdo;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public List<CuotaDto> getCuotasList() {
        return cuotasList;
    }

    public void setCuotasList(List<CuotaDto> cuotasList) {
        this.cuotasList = cuotasList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
