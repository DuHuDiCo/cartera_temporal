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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "discriminacion")
public class Discriminacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discriminacion")
    private Long idDiscriminacion;
    
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "numero_obligacion", nullable = false)
    private String numeroObligacion;
    
    @Column(name = "nombre_cliente", nullable = true)
    private String nombreCliente;
    
    @Column(name = "fecha_creacion_gestion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionGestion;
    
    @Column(name = "fecha_acuerdo")
    @Temporal(TemporalType.DATE)
    private Date fechaAcuerdo;
    
    @Column(name = "discriminacion_valor_capital")
    private Double discriminacionValorCapital;
    
    @Column(name = "discriminacion_valor_intereses")
    private Double discriminacionValorIntereses;
    
    @Column(name = "discriminacion_valor_honorarios")
    private Double discriminacionValorHonorarios;
    
    @Column(name = "observacion_discriminacion")
    private String observacionDiscriminacion;
    
    @Column(name = "tipo_discriminante")
    private String tipoDiscriminante;
    
    @ManyToOne
    @JoinColumn(name = "gestion_id")
    @JsonIgnore
    private Gestiones gestiones;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesorCartera;

    public Discriminacion() {
    }

    public Long getIdDiscriminacion() {
        return idDiscriminacion;
    }

    public void setIdDiscriminacion(Long idDiscriminacion) {
        this.idDiscriminacion = idDiscriminacion;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    public Double getDiscriminacionValorCapital() {
        return discriminacionValorCapital;
    }

    public void setDiscriminacionValorCapital(Double discriminacionValorCapital) {
        this.discriminacionValorCapital = discriminacionValorCapital;
    }

    public Double getDiscriminacionValorIntereses() {
        return discriminacionValorIntereses;
    }

    public void setDiscriminacionValorIntereses(Double discriminacionValorIntereses) {
        this.discriminacionValorIntereses = discriminacionValorIntereses;
    }

    public Double getDiscriminacionValorHonorarios() {
        return discriminacionValorHonorarios;
    }

    public void setDiscriminacionValorHonorarios(Double discriminacionValorHonorarios) {
        this.discriminacionValorHonorarios = discriminacionValorHonorarios;
    }

    public String getTipoDiscriminante() {
        return tipoDiscriminante;
    }

    public void setTipoDiscriminante(String tipoDiscriminante) {
        this.tipoDiscriminante = tipoDiscriminante;
    }

    public Gestiones getGestiones() {
        return gestiones;
    }

    public void setGestiones(Gestiones gestiones) {
        this.gestiones = gestiones;
    }

    public AsesorCartera getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(AsesorCartera asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public Date getFechaCreacionGestion() {
        return fechaCreacionGestion;
    }

    public void setFechaCreacionGestion(Date fechaCreacionGestion) {
        this.fechaCreacionGestion = fechaCreacionGestion;
    }

    public String getObservacionDiscriminacion() {
        return observacionDiscriminacion;
    }

    public void setObservacionDiscriminacion(String observacionDiscriminacion) {
        this.observacionDiscriminacion = observacionDiscriminacion;
    }
    
}
