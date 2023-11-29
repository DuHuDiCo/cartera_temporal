package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class CorreoElectronicoDto {

    private Long idCorreoElectronico;
    private String email;
    private Date fechaCreacion;
    private boolean isCurrent;

    public CorreoElectronicoDto() {
    }

    public Long getIdCorreoElectronico() {
        return idCorreoElectronico;
    }

    public void setIdCorreoElectronico(Long idCorreoElectronico) {
        this.idCorreoElectronico = idCorreoElectronico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
    
    
    

}
