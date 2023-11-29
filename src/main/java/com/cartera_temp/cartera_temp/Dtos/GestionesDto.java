package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class GestionesDto {

    private String numeroObligacion;

    private String cedula;

    private String cliente;

    private String sede;

    private String banco;

    private String asesor;

    private Date fechaGestion;

    private Date fechaCompromiso;

    private String clasificacion;

    private String gestion;
    
    private boolean contact;

    private double valorCompromiso;

    private String detallesAdicionales;

    private String gestionLlamada;

    public GestionesDto() {
    }

    public GestionesDto(String numeroObligacion, String cedula, String cliente, String sede, String banco, String asesor, Date fechaGestion, Date fechaCompromiso, String clasificacion, String gestion, double valorCompromiso, String detallesAdicionales, String gestionLlamada) {
        this.numeroObligacion = numeroObligacion;
        this.cedula = cedula;
        this.cliente = cliente;
        this.sede = sede;
        this.banco = banco;
        this.asesor = asesor;
        this.fechaGestion = fechaGestion;
        this.fechaCompromiso = fechaCompromiso;
        this.clasificacion = clasificacion;
        this.gestion = gestion;
        this.valorCompromiso = valorCompromiso;
        this.detallesAdicionales = detallesAdicionales;
        this.gestionLlamada = gestionLlamada;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public String getGestionLlamada() {
        return gestionLlamada;
    }

    public void setGestionLlamada(String gestionLlamada) {
        this.gestionLlamada = gestionLlamada;
    }

    public double getValorCompromiso() {
        return valorCompromiso;
    }

    public void setValorCompromiso(double valorCompromiso) {
        this.valorCompromiso = valorCompromiso;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

  

    
    
}
