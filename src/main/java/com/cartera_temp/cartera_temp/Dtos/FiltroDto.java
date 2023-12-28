package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;


public class FiltroDto {

    private String banco;
    private Integer diasVencidosInicio;
    private Integer diasVencidosFin;
    private String edadVencimiento;
    private String sede;
    private String clasiJuridica;
    private Double saldoCapitalInicio;
    private Double saldoCapitalFin;
    private Date fechaCpcInicio;
    private Date fechaCpcFin;
    private Date fechaGestionInicio;
    private Date fechaGestionFin;
    private Date fechaCompromisoInicio;
    private Date fechaCompromisoFin;

    public FiltroDto() {
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getDiasVencidosInicio() {
        return diasVencidosInicio;
    }

    public void setDiasVencidosInicio(Integer diasVencidosInicio) {
        this.diasVencidosInicio = diasVencidosInicio;
    }

    public Integer getDiasVencidosFin() {
        return diasVencidosFin;
    }

    public void setDiasVencidosFin(Integer diasVencidosFin) {
        this.diasVencidosFin = diasVencidosFin;
    }

    public String getEdadVencimiento() {
        return edadVencimiento;
    }

    public void setEdadVencimiento(String edadVencimiento) {
        this.edadVencimiento = edadVencimiento;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getClasiJuridica() {
        return clasiJuridica;
    }

    public void setClasiJuridica(String clasiJuridica) {
        this.clasiJuridica = clasiJuridica;
    }

    public Double getSaldoCapitalInicio() {
        return saldoCapitalInicio;
    }

    public void setSaldoCapitalInicio(double saldoCapitalInicio) {
        this.saldoCapitalInicio = saldoCapitalInicio;
    }

    public Date getFechaCpcInicio() {
        return fechaCpcInicio;
    }

    public void setFechaCpcInicio(Date fechaCpcInicio) {
        this.fechaCpcInicio = fechaCpcInicio;
    }

    public Date getFechaCpcFin() {
        return fechaCpcFin;
    }

    public void setFechaCpcFin(Date fechaCpcFin) {
        this.fechaCpcFin = fechaCpcFin;
    }

    public Date getFechaGestionInicio() {
        return fechaGestionInicio;
    }

    public void setFechaGestionInicio(Date fechaGestionInicio) {
        this.fechaGestionInicio = fechaGestionInicio;
    }

    public Date getFechaGestionFin() {
        return fechaGestionFin;
    }

    public void setFechaGestionFin(Date fechaGestionFin) {
        this.fechaGestionFin = fechaGestionFin;
    }

    public Date getFechaCompromisoInicio() {
        return fechaCompromisoInicio;
    }

    public void setFechaCompromisoInicio(Date fechaCompromisoInicio) {
        this.fechaCompromisoInicio = fechaCompromisoInicio;
    }

    public Date getFechaCompromisoFin() {
        return fechaCompromisoFin;
    }

    public void setFechaCompromisoFin(Date fechaCompromisoFin) {
        this.fechaCompromisoFin = fechaCompromisoFin;
    }

    public Double getSaldoCapitalFin() {
        return saldoCapitalFin;
    }

    public void setSaldoCapitalFin(Double saldoCapitalFin) {
        this.saldoCapitalFin = saldoCapitalFin;
    }
    
    
    
}
