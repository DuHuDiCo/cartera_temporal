package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "gestiones")
public class Gestiones {
    
    @Id
    @GeneratedValue    
    @Column(name = "id_gestion")
    private Long idGestion;
    
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "numero_obligacion", nullable = false)
    private String numeroObligacion;
    
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "numero_doc", nullable = false)
    private String numeroDoc;
    
    @Column(name = "nombre_cliente", nullable = true)
    private String nombreCliente;
    
    @Column(name = "fecha_gestion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGestion;
    
    @Column(name = "fecha_compromisos", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaCompromiso;
    
    @Column(name = "clasificacion", nullable =true)
    private String clasificacion;
    
    @Column(name = "gestion")
    private String gestion;
    
    @Column(name = "valor_compromiso", nullable = true)
    private String valorCompromiso;
    
    @Column(name = "datos_adicionales", nullable = true)
    private String gestionLlamada;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesorCartera;
    
    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    private Banco banco;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    private Sede sede;
    
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

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getValorCompromiso() {
        return valorCompromiso;
    }

    public void setValorCompromiso(String valorCompromiso) {
        this.valorCompromiso = valorCompromiso;
    }

    public String getGestionLlamada() {
        return gestionLlamada;
    }

    public void setGestionLlamada(String gestionLlamada) {
        this.gestionLlamada = gestionLlamada;
    }

    public AsesorCartera getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(AsesorCartera asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public CuentasPorCobrar getCuentasPorCobrar() {
        return cuentasPorCobrar;
    }

    public void setCuentasPorCobrar(CuentasPorCobrar cuentasPorCobrar) {
        this.cuentasPorCobrar = cuentasPorCobrar;
    }
    
    
    
}
