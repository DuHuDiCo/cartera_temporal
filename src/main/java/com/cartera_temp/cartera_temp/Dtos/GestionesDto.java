package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class GestionesDto {

    private String numeroObligacion;

    private String asesor;
    
    private Date fechaGestion;
    
    private String clasificacion;

    private String detallesAdicionales;

    public GestionesDto() {
    }

    public GestionesDto(String numeroObligacion, String asesor, Date fechaGestion, String clasificacion, String detallesAdicionales) {
        this.numeroObligacion = numeroObligacion;
        this.asesor = asesor;
        this.fechaGestion = fechaGestion;
        this.clasificacion = clasificacion;
        this.detallesAdicionales = detallesAdicionales;
    }


    

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

     
}
