package com.cartera_temp.cartera_temp.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cuentas_por_cobrar")
public class CuentasPorCobrar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_por_cobrar")
    private Long idCuentasPorCobrar;
    
    @Column(name = "numero_obligacion")
    private String numeroObligacion;
    
    @Column(name = "cliente")
    private String cliente;

    @Column(name = "documento_cliente")
    private String documentoCliente;

    @Column(name = "fecha_cuenta_cobrar")
    @Temporal(TemporalType.DATE)
    private Date fechaCuentaCobrar;

    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "valor_nota_debito")
    private double valorNotaDebito;

    @Column(name = "valor_cuota")
    private double valorCuota;

    @Column(name = "valor_pagos")
    private double valorPagos;

    @Column(name = "nombre_usuario", length = 30)
    private String nombre_usuario;

    @Column(name = "clasificacion", length = 100)
    private String clasificacion;

    @Column(name = "vendedor", length = 200)
    private String vendedor;

    @Column(name = "clasificacionJuridaca", length = 100)
    private String clasificacionJuridica;

    @Column(name = "detalle")
    private String detalle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id", referencedColumnName = "id_sede")
    private Sede sede;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "banco_id", referencedColumnName = "id_banco")
    private Banco banco;
    
    @OneToMany(mappedBy = "cuentasPorCobrar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gestiones> gestiones = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_cartera_id", referencedColumnName = "id_asesor_cartera")
    private AsesorCartera asesor;

    @Column(name = "dias_vencidos")
    private int diasVencidos;

    @Column(name = "edad_vencimiento", length = 50)
    private String edadVencimiento;

    @Column(name = "condicion_especial", length = 30)
    private String condicionEspecial;

    @Column(name = "numero_creditos")
    private int numeroCreditos;

    @Column(name = "pagare")
    private String pagare;

    @Column(name = "mora_obligatoria")
    private double moraObligatoria;

    @Column(name = "cuotas_mora")
    private int cuotasMora;

    @Column(name = "cuotas")
    private int cuotas;

    public CuentasPorCobrar() {
    }
    
    public void agregarGestion(Gestiones gestion){
        gestiones.add(gestion);
        gestion.setCuentasPorCobrar(this);
    }
    
    public Long getIdCuentasPorCobrar() {
        return idCuentasPorCobrar;
    }

    public void setIdCuentasPorCobrar(Long idCuentasPorCobrar) {
        this.idCuentasPorCobrar = idCuentasPorCobrar;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public Date getFechaCuentaCobrar() {
        return fechaCuentaCobrar;
    }

    public void setFechaCuentaCobrar(Date fechaCuentaCobrar) {
        this.fechaCuentaCobrar = fechaCuentaCobrar;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorNotaDebito() {
        return valorNotaDebito;
    }

    public void setValorNotaDebito(double valorNotaDebito) {
        this.valorNotaDebito = valorNotaDebito;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getValorPagos() {
        return valorPagos;
    }

    public void setValorPagos(double valorPagos) {
        this.valorPagos = valorPagos;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public AsesorCartera getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorCartera asesor) {
        this.asesor = asesor;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

    public String getEdadVencimiento() {
        return edadVencimiento;
    }

    public void setEdadVencimiento(String edadVencimiento) {
        this.edadVencimiento = edadVencimiento;
    }

    public String getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public String getPagare() {
        return pagare;
    }

    public void setPagare(String pagare) {
        this.pagare = pagare;
    }

    public double getMoraObligatoria() {
        return moraObligatoria;
    }

    public void setMoraObligatoria(double moraObligatoria) {
        this.moraObligatoria = moraObligatoria;
    }

    public int getCuotasMora() {
        return cuotasMora;
    }

    public void setCuotasMora(int cuotasMora) {
        this.cuotasMora = cuotasMora;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getClasificacionJuridica() {
        return clasificacionJuridica;
    }

    public void setClasificacionJuridica(String clasificacionJuridica) {
        this.clasificacionJuridica = clasificacionJuridica;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public List<Gestiones> getGestiones() {
        return gestiones;
    }

    public void setGestiones(List<Gestiones> gestiones) {
        this.gestiones = gestiones;
    }

    
}
