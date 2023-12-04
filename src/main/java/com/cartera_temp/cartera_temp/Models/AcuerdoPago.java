package com.cartera_temp.cartera_temp.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AcuerdoPago extends Clasificacion {

    @OneToOne(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private Gestiones gestion;

    @Column(name = "detalle", length = 2000)
    private String detalle;

    @Column(name = "valor_couta_mensual")
    private double valorCuotaMensual;

    @Column(name = "tipo_acuerdo")
    private String tipoAcuerdo;

    @Column(name = "valor_total_acuerdo")
    private double valorTotalAcuerdo;

    @Column(name = "valor_intereses_mora")
    private double valorInteresesMora;

    @Column(name = "horario_acuerdo")
    private double honoriarioAcuerdo;

    @Column(name = "fecha_acuerdo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcuerdo;

    @Column(name = "fecha_compromiso")
    @Temporal(TemporalType.DATE)
    private Date fechaCompromiso;
    
    @OneToMany(mappedBy = "acuerdoPago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuotas> cuotasList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_cartera_id", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesor;

    public AcuerdoPago() {
    }
    
    public void agregarCuota(Cuotas cuota){
        cuotasList.add(cuota);
        cuota.setAcuerdoPago(this);
    }

    public Gestiones getGestion() {
        return gestion;
    }

    public void setGestion(Gestiones gestion) {
        this.gestion = gestion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getValorCuotaMensual() {
        return valorCuotaMensual;
    }

    public void setValorCuotaMensual(double valorCuotaMensual) {
        this.valorCuotaMensual = valorCuotaMensual;
    }

    public String getTipoAcuerdo() {
        return tipoAcuerdo;
    }

    public void setTipoAcuerdo(String tipoAcuerdo) {
        this.tipoAcuerdo = tipoAcuerdo;
    }

    public double getValorTotalAcuerdo() {
        return valorTotalAcuerdo;
    }

    public void setValorTotalAcuerdo(double valorTotalAcuerdo) {
        this.valorTotalAcuerdo = valorTotalAcuerdo;
    }

    public double getValorInteresesMora() {
        return valorInteresesMora;
    }

    public void setValorInteresesMora(double valorInteresesMora) {
        this.valorInteresesMora = valorInteresesMora;
    }

    public double getHonoriarioAcuerdo() {
        return honoriarioAcuerdo;
    }

    public void setHonoriarioAcuerdo(double honoriarioAcuerdo) {
        this.honoriarioAcuerdo = honoriarioAcuerdo;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public AsesorCartera getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorCartera asesor) {
        this.asesor = asesor;
    }

    
    
}
