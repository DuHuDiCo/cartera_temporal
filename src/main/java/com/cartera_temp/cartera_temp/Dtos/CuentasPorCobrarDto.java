
package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CuentasPorCobrarDto {
    
    private String numeroObligacion;
    
    @NotBlank
    @NotNull
    private String cliente;
    
    @NotBlank
    @NotNull
    private Date fechaCuenta;
    
    @NotBlank
    @NotNull
    private Date fechaVencimiento;
    
    @NotBlank
    @NotNull
    private String tipo;
    
    
    private double valorNotaDebito;
    
    @NotBlank
    @NotNull
    private double valorCuota;
    
    
    private double valorPagos;
    
    
    private String nombreUsuario;
    
    private String clasificacion;
    
    @NotBlank
    @NotNull
    private String vendedor;
    
    private String detalle;
    
    @NotBlank
    @NotNull
    private String sede;
    
    @NotBlank
    @NotNull
    private String banco;
    
    @NotBlank
    @NotNull
    private String cedula;
    
    private String clasificacionJuridica;
    
    @NotBlank
    @NotNull
    private String asesorCartera;
    
    
    private int diasVencidos;
    
    private String condicionEspecial;
    
    private String edadVencimiento;
    
    private int numeroCreditos;
    
    private String pagare;
    
    private double moraObligatoria;
    
    private double totalObligacion;
    
    private int coutasMora;
    
    private int numeroCuotas;

    public CuentasPorCobrarDto() {
    }

    public CuentasPorCobrarDto(String numeroObligacion, String cliente, Date fechaCuenta, Date fechaVencimiento, String tipo, double valorNotaDebito, double valorCuota, double valorPagos, String nombreUsuario, String clasificacion, String vendedor, String detalle, String sede, String banco, String cedula, String clasificacionJuridica, String asesorCartera, int diasVencidos, String condicionEspecial, String edadVencimiento, int numeroCreditos, String pagare, double moraObligatoria, double totalObligacion, int coutasMora, int numeroCuotas) {
        this.numeroObligacion = numeroObligacion;
        this.cliente = cliente;
        this.fechaCuenta = fechaCuenta;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.valorNotaDebito = valorNotaDebito;
        this.valorCuota = valorCuota;
        this.valorPagos = valorPagos;
        this.nombreUsuario = nombreUsuario;
        this.clasificacion = clasificacion;
        this.vendedor = vendedor;
        this.detalle = detalle;
        this.sede = sede;
        this.banco = banco;
        this.cedula = cedula;
        this.clasificacionJuridica = clasificacionJuridica;
        this.asesorCartera = asesorCartera;
        this.diasVencidos = diasVencidos;
        this.condicionEspecial = condicionEspecial;
        this.edadVencimiento = edadVencimiento;
        this.numeroCreditos = numeroCreditos;
        this.pagare = pagare;
        this.moraObligatoria = moraObligatoria;
        this.totalObligacion = totalObligacion;
        this.coutasMora = coutasMora;
        this.numeroCuotas = numeroCuotas;
    }

 
    
    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFechaCuenta() {
        return fechaCuenta;
    }

    public void setFechaCuenta(Date fechaCuenta) {
        this.fechaCuenta = fechaCuenta;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClasificacionJuridica() {
        return clasificacionJuridica;
    }

    public void setClasificacionJuridica(String clasificacionJuridica) {
        this.clasificacionJuridica = clasificacionJuridica;
    }

    public String getAsesorCartera() {
        return asesorCartera;
    }

    public void setAsesorCartera(String asesorCartera) {
        this.asesorCartera = asesorCartera;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
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

   

    public double getMoraObligatoria() {
        return moraObligatoria;
    }

    public void setMoraObligatoria(double moraObligatoria) {
        this.moraObligatoria = moraObligatoria;
    }

    public double getTotalObligacion() {
        return totalObligacion;
    }

    public void setTotalObligacion(double totalObligacion) {
        this.totalObligacion = totalObligacion;
    }

    public int getCoutasMora() {
        return coutasMora;
    }

    public void setCoutasMora(int coutasMora) {
        this.coutasMora = coutasMora;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getEdadVencimiento() {
        return edadVencimiento;
    }

    public void setEdadVencimiento(String edadVencimiento) {
        this.edadVencimiento = edadVencimiento;
    }

    public String getPagare() {
        return pagare;
    }

    public void setPagare(String pagare) {
        this.pagare = pagare;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }
    
    
    
    
           
    

}
