package com.cartera_temp.cartera_temp.Dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FiltroDto {

    private List<String> banco = new ArrayList<>();
    private Integer diasVencidosInicio;
    private Integer diasVencidosFin;
    private List<String> edadVencimiento = new ArrayList<>();
    private List<String> sede = new ArrayList<>();
    private String username;
    private boolean isActive;
    private List<String> clasiJuridica = new ArrayList<>();
    private Double saldoCapitalInicio;
    private Double saldoCapitalFin;
    private Date fechaCpcInicio;
    private Date fechaCpcFin;
    private Date fechaGestionInicio;
    private Date fechaGestionFin;
    private String fechaCompromisoInicio;
    private String fechaCompromisoFin;
    private ClasificacionGestionFiltro clasificacionGestion;

    public FiltroDto() {
    }

    public List<String> getBanco() {
        return banco;
    }

    public void setBanco(List<String> banco) {
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

    public List<String> getEdadVencimiento() {
        return edadVencimiento;
    }

    public void setEdadVencimiento(List<String> edadVencimiento) {
        this.edadVencimiento = edadVencimiento;
    }

    public List<String> getSede() {
        return sede;
    }

    public void setSede(List<String> sede) {
        this.sede = sede;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getClasiJuridica() {
        return clasiJuridica;
    }

    public void setClasiJuridica(List<String> clasiJuridica) {
        this.clasiJuridica = clasiJuridica;
    }

    public Double getSaldoCapitalInicio() {
        return saldoCapitalInicio;
    }

    public void setSaldoCapitalInicio(Double saldoCapitalInicio) {
        this.saldoCapitalInicio = saldoCapitalInicio;
    }

    public Double getSaldoCapitalFin() {
        return saldoCapitalFin;
    }

    public void setSaldoCapitalFin(Double saldoCapitalFin) {
        this.saldoCapitalFin = saldoCapitalFin;
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

    public String getFechaCompromisoInicio() {
        return fechaCompromisoInicio;
    }

    public void setFechaCompromisoInicio(String fechaCompromisoInicio) {
        this.fechaCompromisoInicio = fechaCompromisoInicio;
    }

    public String getFechaCompromisoFin() {
        return fechaCompromisoFin;
    }

    public void setFechaCompromisoFin(String fechaCompromisoFin) {
        this.fechaCompromisoFin = fechaCompromisoFin;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ClasificacionGestionFiltro getClasificacionGestion() {
        return clasificacionGestion;
    }

    public void setClasificacionGestion(ClasificacionGestionFiltro clasificacionGestion) {
        this.clasificacionGestion = clasificacionGestion;
    }

  
   
    
    
}
