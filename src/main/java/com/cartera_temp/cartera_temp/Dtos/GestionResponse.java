package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;

import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import java.util.Date;

public class GestionResponse {

    private Long idGestion;
    private String numeroObligacion;

    private Date fechaGestion;
    private String detallesGestion;
    private String detallesAdicionales;

    private String asesorCartera;

    private ClasificacionGestion clasificacion;

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

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public ClasificacionGestion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionGestion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public CuentasPorCobrar getCpc() {
        return cpc;
    }

    public void setCpc(CuentasPorCobrar cpc) {
        this.cpc = cpc;
    }

    public String getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(String asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public String getDetallesGestion() {
        return detallesGestion;
    }

    public void setDetallesGestion(String detallesGestion) {
        this.detallesGestion = detallesGestion;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    
    
}
