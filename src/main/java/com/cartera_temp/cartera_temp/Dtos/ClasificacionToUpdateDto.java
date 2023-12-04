package com.cartera_temp.cartera_temp.Dtos;

public class ClasificacionToUpdateDto {

    private Long idClasificacion;
    
    private String clasificacionToUpdate;

    public ClasificacionToUpdateDto() {
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getClasificacionToUpdate() {
        return clasificacionToUpdate;
    }

    public void setClasificacionToUpdate(String clasificacionToUpdate) {
        this.clasificacionToUpdate = clasificacionToUpdate;
    }
    
}
