
package com.cartera_temp.cartera_temp.Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notificaciones")
public class Notificaciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificaciones;
    
    @Column(name = "tipo_gestion", length = 50)
    private String tipoGestion;
    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @Column(name = "fecha_finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    
   
    
     @Column(name = "id_designated_to")
    private Long designatedTo;

    public Notificaciones() {
    }

    public Long getIdNotificaciones() {
        return idNotificaciones;
    }

    public void setIdNotificaciones(Long idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public String getTipoGestion() {
        return tipoGestion;
    }

    public void setTipoGestion(String tipoGestion) {
        this.tipoGestion = tipoGestion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }


    public Long getDesignatedTo() {
        return designatedTo;
    }

    public void setDesignatedTo(Long designatedTo) {
        this.designatedTo = designatedTo;
    }
     
     

}
