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

    @Column(name = "numero_obligacion", length = 50)
    private String numeroObligacion;

    @Column(name = "cleinte", length = 150)
    private String cliente;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "id_designated_by")
    private String designatedBy;

    @Column(name = "id_designated_to")
    private Long designatedTo;
    
    @Column(name = "ver_realizadas")
    private String verRealizadas;
    
    @Column(name = "gestion_id")
    private Long gestionId;

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

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getDesignatedBy() {
        return designatedBy;
    }

    public void setDesignatedBy(String designatedBy) {
        this.designatedBy = designatedBy;
    }
    public String getVerRealizadas() {
        return verRealizadas;
    }

    public void setVerRealizadas(String verRealizadas) {
        this.verRealizadas = verRealizadas;
    }

    public Long getGestionId() {
        return gestionId;
    }

    public void setGestionId(Long gestionId) {
        this.gestionId = gestionId;
    }

    

    
    
}
