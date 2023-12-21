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


@Entity
@Table(name = "cuotas")
public class Cuotas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_cuota")
    private Long idCuota;
    
    @Column(name = "numero_cuota")
    private int numeroCuota;
    
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    
    @Column(name = "valor_cuota")
    private double valorCuota;
    
    @Column(name = "capital_cuota")
    private double capitalCuota;
    
    @Column(name = "honorarios" )
    private double honorarios;
    
    @Column(name = "interes_cuota")
    private double interesCuota;
    
    @Column(name = "cumplio")
    private boolean cumplio;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pago_id", referencedColumnName = "id_pago")
    private Pagos pagos;
    
    @ManyToOne
    @JoinColumn(name = "acuerdo_pago_id")
    @JsonIgnore
    private AcuerdoPago acuerdoPago;

    public Cuotas() {
    }

    public Long getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(Long idCuota) {
        this.idCuota = idCuota;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getCapitalCuota() {
        return capitalCuota;
    }

    public void setCapitalCuota(double capitalCuota) {
        this.capitalCuota = capitalCuota;
    }

    public double getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(double honorarios) {
        this.honorarios = honorarios;
    }

    public boolean isCumplio() {
        return cumplio;
    }

    public void setCumplio(boolean cumplio) {
        this.cumplio = cumplio;
    }

    public AcuerdoPago getAcuerdoPago() {
        return acuerdoPago;
    }

    public void setAcuerdoPago(AcuerdoPago acuerdoPago) {
        this.acuerdoPago = acuerdoPago;
    }

    public double getInteresCuota() {
        return interesCuota;
    }

    public void setInteresCuota(double interesCuota) {
        this.interesCuota = interesCuota;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    
    
    
}
