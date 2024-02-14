
package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;


public class NotificacionRequest {
    
    private Long idNotificacion;
    private Date fechaCreacion;
    private Long idClasificacion;

    public NotificacionRequest() {
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }
    
    

}
