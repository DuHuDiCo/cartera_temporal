package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Models.Clasificacion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Sede;
import java.util.Date;

public class GestionResponse {

    private Long idGestion;
    private String numeroObligacion;
    private String numeroDoc;
    private String nombreCliente;
    private Date fechaGestion;
    private Date fechaCompromiso;
    private String gestion;
    private boolean isContacted;
    private double valorCompromiso;
    private String asesorCartera;
    private Banco banco;
    private Clasificacion clasificacion;
    private Sede sede;
    private CuentasPorCobrar cpc;

    public GestionResponse() {
    }

    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public boolean isIsContacted() {
        return isContacted;
    }

    public void setIsContacted(boolean isContacted) {
        this.isContacted = isContacted;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public CuentasPorCobrar getCpc() {
        return cpc;
    }

    public void setCpc(CuentasPorCobrar cpc) {
        this.cpc = cpc;
    }
    
    
    
}
