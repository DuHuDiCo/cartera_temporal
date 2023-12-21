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
@Table(name = "historico_acuerdos_pago")
public class HistoricoAcuerdosPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_acuerdos")
    private Long idHistoricoAcuerdos;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "historio_acuerdo", columnDefinition = "TEXT")
    private String historico;

    @Column(name = "total_valor_cuotas_pagadas")
    private double totalValorCuotasPagadas;

    @Column(name = "valor_total_acuerdo")
    private double valorTotalAcuerdo;

    @Column(name = "total_cuotas_acuerdo")
    private int totalCuotasAcuerdo;

    @Column(name = "total_cuotas_pagadas")
    private int totalCuotasPagadas;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion_historico")
    private Date fechaCreacionAcuerdo;

    @Column(name = "asesor")
    private String asesorCartera;

    public HistoricoAcuerdosPago() {
    }

    public Long getIdHistoricoAcuerdos() {
        return idHistoricoAcuerdos;
    }

    public void setIdHistoricoAcuerdos(Long idHistoricoAcuerdos) {
        this.idHistoricoAcuerdos = idHistoricoAcuerdos;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public double getTotalValorCuotasPagadas() {
        return totalValorCuotasPagadas;
    }

    public void setTotalValorCuotasPagadas(double totalValorCuotasPagadas) {
        this.totalValorCuotasPagadas = totalValorCuotasPagadas;
    }

    public double getValorTotalAcuerdo() {
        return valorTotalAcuerdo;
    }

    public void setValorTotalAcuerdo(double valorTotalAcuerdo) {
        this.valorTotalAcuerdo = valorTotalAcuerdo;
    }

    public int getTotalCuotasAcuerdo() {
        return totalCuotasAcuerdo;
    }

    public void setTotalCuotasAcuerdo(int totalCuotasAcuerdo) {
        this.totalCuotasAcuerdo = totalCuotasAcuerdo;
    }

    public int getTotalCuotasPagadas() {
        return totalCuotasPagadas;
    }

    public void setTotalCuotasPagadas(int totalCuotasPagadas) {
        this.totalCuotasPagadas = totalCuotasPagadas;
    }

 

    public Date getFechaCreacionAcuerdo() {
        return fechaCreacionAcuerdo;
    }

    public void setFechaCreacionAcuerdo(Date fechaCreacionAcuerdo) {
        this.fechaCreacionAcuerdo = fechaCreacionAcuerdo;
    }

    public String getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(String asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

}
