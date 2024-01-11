package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class GestionToSaveDto {

    
    private String numeroObligacion;
    
    private ClasificacionDtoTipo clasificacion;
    
    private boolean contact;
    
    private String detallesAdicionales;
    
    private Long asignatedTo;

    public GestionToSaveDto() {
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
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

    public ClasificacionDtoTipo getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionDtoTipo clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Long getAsignatedTo() {
        return asignatedTo;
    }

    public void setAsignatedTo(Long asignatedTo) {
        this.asignatedTo = asignatedTo;
    }
    
    
}
