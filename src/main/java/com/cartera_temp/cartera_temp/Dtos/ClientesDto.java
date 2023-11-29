package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientesDto {

    private Long idCliente;

    private String nit;

    private String tipoDoc;

    private String numeroDocumento;

    private String nombreTitular;

    private String numeroObligacion;

    private Set<Direccion> direcciones = new HashSet<>();
    private Set<Telefono> telefonos = new HashSet<>();
    private Set<CorreoElectronico> correos= new HashSet<>();
    private String descripcionDetallada;

    private boolean status;

    private Date fechaNacimiento;

    private Date fechaEmision;

    private String vendedor;

    private String departamento;

    private String municipio;

    private String clasificacionJur;

    private double saldoActual;

    private double saldoVencido;

    private double montoUltimoPago;

    private Date fechaUltimoPago;
    private double pagosEfectuados;

    private double montoUltimaVenta;

    private String sede;

    private String banco;

    private TipoGarante tipoGarante;

    public ClientesDto() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public Set<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Set<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<CorreoElectronico> getCorreos() {
        return correos;
    }

    public void setCorreos(Set<CorreoElectronico> correos) {
        this.correos = correos;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getClasificacionJur() {
        return clasificacionJur;
    }

    public void setClasificacionJur(String clasificacionJur) {
        this.clasificacionJur = clasificacionJur;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getSaldoVencido() {
        return saldoVencido;
    }

    public void setSaldoVencido(double saldoVencido) {
        this.saldoVencido = saldoVencido;
    }

    public double getMontoUltimoPago() {
        return montoUltimoPago;
    }

    public void setMontoUltimoPago(double montoUltimoPago) {
        this.montoUltimoPago = montoUltimoPago;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public double getPagosEfectuados() {
        return pagosEfectuados;
    }

    public void setPagosEfectuados(double pagosEfectuados) {
        this.pagosEfectuados = pagosEfectuados;
    }

    public double getMontoUltimaVenta() {
        return montoUltimaVenta;
    }

    public void setMontoUltimaVenta(double montoUltimaVenta) {
        this.montoUltimaVenta = montoUltimaVenta;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public TipoGarante getTipoGarante() {
        return tipoGarante;
    }

    public void setTipoGarante(TipoGarante tipoGarante) {
        this.tipoGarante = tipoGarante;
    }
    
    
    
}
