
package com.cartera_temp.cartera_temp.Models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarea  extends Clasificacion{
    
    @OneToOne(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private Gestiones gestion;
    
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
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clasificacion_tarea_id", referencedColumnName = "id_clasificacion_tarea")
    private ClasificacionTarea clasificacionTarea;

    public Tarea() {
    }
    
    

    public Gestiones getGestion() {
        return gestion;
    }

    public void setGestion(Gestiones gestion) {
        this.gestion = gestion;
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

    public ClasificacionTarea getClasificacionTarea() {
        return clasificacionTarea;
    }

    public void setClasificacionTarea(ClasificacionTarea clasificacionTarea) {
        this.clasificacionTarea = clasificacionTarea;
    }
    
    
    
    


}
