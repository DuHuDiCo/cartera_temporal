package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Nota extends ClasificacionGestion {


    @OneToOne(mappedBy = "clasificacionGestion", cascade = CascadeType.ALL)
    @JsonIgnore
    private Gestiones gestiones;

    @Column(name = "detalle_nota", columnDefinition = "TEXT")
    private String detalleNota;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nota")
    private Date fechaNota;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_cartera_id", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesor;
    
    
    @ManyToOne
    @JoinColumn(name = "tipo_clasificacion_id")
    private NombresClasificacion nombresClasificacion;

    public Nota() {
    }

    public Gestiones getGestiones() {
        return gestiones;
    }

    public void setGestiones(Gestiones gestiones) {
        this.gestiones = gestiones;
    }

    
    

    public String getDetalleNota() {
        return detalleNota;
    }

    public void setDetalleNota(String detalleNota) {
        this.detalleNota = detalleNota;
    }

    public Date getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(Date fechaNota) {
        this.fechaNota = fechaNota;
    }

    public AsesorCartera getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorCartera asesor) {
        this.asesor = asesor;
    }

    public NombresClasificacion getNombresClasificacion() {
        return nombresClasificacion;
    }

    public void setNombresClasificacion(NombresClasificacion nombresClasificacion) {
        this.nombresClasificacion = nombresClasificacion;
    }

    
}
