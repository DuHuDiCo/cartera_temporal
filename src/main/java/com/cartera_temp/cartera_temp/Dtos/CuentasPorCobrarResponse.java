package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Sede;
import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuentasPorCobrarResponse {

    private Long idCuentasPorCobrar;
    private String numeroObligacion;
    private String cliente;
    private String documentoCliente;
    private Date fechaCuentaCobrar;
    private Date fechaVencimiento;
    private String tipo;
    private double valorNotaDebito;
    private double valorCuota;
    private double valorPagos;
    private String nombre_usuario;
    private String clasificacion;
    private String vendedor;
    private String clasificacionJuridica;
    private String detalle;
    private Sede sede;
    private Banco banco;
    private int diasVencidos;
    private List<Gestiones> gestion = new ArrayList<>();
    private TiposVencimiento tiposVencimiento;
    private String condicionEspecial;
    private int numeroCreditos;
    private String pagare;
    private double moraObligatoria;
    private double totalObligatoria;
    private int cuotasMora;
    private int cuotas;
    private AsesorCarteraResponse asesorCarteraResponse;
    private List<ClientesDto> clientes = new ArrayList<>();
    private Boolean isLast = false;
    private Boolean isBlocked;

    public CuentasPorCobrarResponse() {
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

    public double getTotalObligatoria() {
        return totalObligatoria;
    }

    public void setTotalObligatoria(double totalObligatoria) {
        this.totalObligatoria = totalObligatoria;
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

    public String getClasificacionJuridica() {
        return clasificacionJuridica;
    }

    public void setClasificacionJuridica(String clasificacionJuridica) {
        this.clasificacionJuridica = clasificacionJuridica;
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

    public AsesorCarteraResponse getAsesorCarteraResponse() {
        return asesorCarteraResponse;
    }

    public void setAsesorCarteraResponse(AsesorCarteraResponse asesorCarteraResponse) {
        this.asesorCarteraResponse = asesorCarteraResponse;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public List<ClientesDto> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClientesDto> clientes) {
        this.clientes = clientes;
    }

    public List<Gestiones> getGestion() {
        return gestion;
    }

    public void setGestion(List<Gestiones> gestion) {
        this.gestion = gestion;
    }

    public TiposVencimiento getTiposVencimiento() {
        return tiposVencimiento;
    }

    public void setTiposVencimiento(TiposVencimiento tiposVencimiento) {
        this.tiposVencimiento = tiposVencimiento;
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

}
