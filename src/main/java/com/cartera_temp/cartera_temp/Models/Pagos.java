package com.cartera_temp.cartera_temp.Models;

import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;

    @Column(name = "detalle")
    private String detalle;

    @JoinColumn(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "saldo_cuota")
    private double saldoCuota;

    @OneToMany(mappedBy = "pagos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cuotas> coutas = new ArrayList<>();

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

    public List<Cuotas> getCoutas() {
        return coutas;
    }

    public void setCoutas(List<Cuotas> coutas) {
        this.coutas = coutas;
    }

}
