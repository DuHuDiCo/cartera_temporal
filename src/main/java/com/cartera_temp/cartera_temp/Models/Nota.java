
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
public class Nota extends Clasificacion{
    
    @OneToOne(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private Gestiones gestion;
    
   @Column(name = "detalle_nota", columnDefinition = "TEXT")
    private String detalleNota;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nota")
    private Date fechaNota;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_cartera_id", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesor;

    public Nota() {
    }

    public Gestiones getGestion() {
        return gestion;
    }

    public void setGestion(Gestiones gestion) {
        this.gestion = gestion;
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
    
    
    
    
    
    


}
