package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class GestionToSaveDto {

    
    private String numeroObligacion;
    
    private Date fechaCompromiso;
    
    private String clasificacion;
    
    private String gestion;
    
    private double valorCompromiso;
    
    private String asesorCartera;
    
    private boolean contact;
    
    private String detallesAdicionales;

    public GestionToSaveDto() {
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

    public double getValorCompromiso() {
        return valorCompromiso;
    }

    public void setValorCompromiso(double valorCompromiso) {
        this.valorCompromiso = valorCompromiso;
    }

    public String getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(String asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }
    
}
