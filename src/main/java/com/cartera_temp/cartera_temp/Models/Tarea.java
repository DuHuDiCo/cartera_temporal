package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarea extends ClasificacionGestion {

    @OneToOne(mappedBy = "clasificacionGestion", cascade = CascadeType.ALL)
    @JsonIgnore
    private Gestiones gestiones;

    @Column(name = "detalle_tarea", columnDefinition = "TEXT")
    private String detalleTarea;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_tarea")
    private Date fechaTarea;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin_tarea")
    private Date fechaFinTarea;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_cartera_id", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesor;
    
    @Column(name = "designated_to")
    private Long designatedTo;
    
    @Column(name = "is_parte_of_recaudo")
    private boolean isParteOfRecaudo;

    @ManyToOne
    @JoinColumn(name = "tipo_clasificacion_id")
    private NombresClasificacion nombresClasificacion;
    
    private boolean  isActive;

    public Tarea() {
    }

    public Gestiones getGestiones() {
        return gestiones;
    }

    public NombresClasificacion getNombresClasificacion() {
        return nombresClasificacion;
    }

    public void setNombresClasificacion(NombresClasificacion nombresClasificacion) {
        this.nombresClasificacion = nombresClasificacion;
    }
    
    

    public void setGestiones(Gestiones gestiones) {
        this.gestiones = gestiones;
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }

    public Date getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(Date fechaTarea) {
        this.fechaTarea = fechaTarea;
    }

    public Date getFechaFinTarea() {
        return fechaFinTarea;
    }

    public void setFechaFinTarea(Date fechaFinTarea) {
        this.fechaFinTarea = fechaFinTarea;
    }

    public AsesorCartera getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorCartera asesor) {
        this.asesor = asesor;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getDesignatedTo() {
        return designatedTo;
    }

    public void setDesignatedTo(Long designatedTo) {
        this.designatedTo = designatedTo;
    }

    public boolean isIsParteOfRecaudo() {
        return isParteOfRecaudo;
    }

    public void setIsParteOfRecaudo(boolean isParteOfRecaudo) {
        this.isParteOfRecaudo = isParteOfRecaudo;
    }

    


}
