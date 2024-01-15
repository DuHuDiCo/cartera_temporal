package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gestiones")
public class Gestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion")
    private Long idGestion;

    @Column(name = "numero_obligacion", nullable = true, length = 30)
    private String numeroObligacion;

    @Column(name = "fecha_gestion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGestion;

    @Column(name = "detalles_gestion", nullable = true, columnDefinition = "TEXT")
    private String detallesGestion;
    
    @Column(name = "detalles_adicionales", nullable = true, columnDefinition = "TEXT")
    private String detallesAdicionales;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesorCartera;
    

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clasificacion_gestion_id")
    private ClasificacionGestion clasificacionGestion;

    @ManyToOne
    @JoinColumn(name = "cuenta_cobrar_id")
    @JsonIgnore
    private CuentasPorCobrar cuentasPorCobrar;

    public Gestiones() {
    }

    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public ClasificacionGestion getClasificacion() {
        return clasificacionGestion;
    }

    public void setClasificacion(ClasificacionGestion clasificacion) {
        this.clasificacionGestion = clasificacion;
    }
    
    
    

   

    public AsesorCartera getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(AsesorCartera asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public CuentasPorCobrar getCuentasPorCobrar() {
        return cuentasPorCobrar;
    }

    public void setCuentasPorCobrar(CuentasPorCobrar cuentasPorCobrar) {
        this.cuentasPorCobrar = cuentasPorCobrar;
    }

  
    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public ClasificacionGestion getClasificacionGestion() {
        return clasificacionGestion;
    }

    public void setClasificacionGestion(ClasificacionGestion clasificacionGestion) {
        this.clasificacionGestion = clasificacionGestion;
    }

    public String getDetallesGestion() {
        return detallesGestion;
    }

    public void setDetallesGestion(String detallesGestion) {
        this.detallesGestion = detallesGestion;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }
    
    
    

}
