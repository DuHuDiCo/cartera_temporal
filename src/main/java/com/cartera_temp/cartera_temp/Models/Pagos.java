package com.cartera_temp.cartera_temp.Models;

import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @Column(name = "valor_pago")
    private double valorPago;

     @Column(name = "valor_capital")
    private double valorCapital;
     
     @Column(name = "valor_intereses")
    private double valorIntereses;
     
     
    @Column(name = "valor_honorarios")
    private double valorHonorarios;
      
    
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;

    @Column(name = "detalle")
    private String detalle;
    
    @JoinColumn(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "saldo_cuota")
    private double saldoCuota;
    
    @ManyToOne
    @JoinColumn(name = "recibo_id")
    private ReciboPago reciboPago;

    public Pagos() {
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

   

    public double getSaldoCuota() {
        return saldoCuota;
    }

    public void setSaldoCuota(double saldoCuota) {
        this.saldoCuota = saldoCuota;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public ReciboPago getReciboPago() {
        return reciboPago;
    }

    public void setReciboPago(ReciboPago reciboPago) {
        this.reciboPago = reciboPago;
    }

    public double getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(double valorCapital) {
        this.valorCapital = valorCapital;
    }

    public double getValorIntereses() {
        return valorIntereses;
    }

    public void setValorIntereses(double valorIntereses) {
        this.valorIntereses = valorIntereses;
    }

    public double getValorHonorarios() {
        return valorHonorarios;
    }

    public void setValorHonorarios(double valorHonorarios) {
        this.valorHonorarios = valorHonorarios;
    }
    
    
    
    
    

}
