package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientesDto {

    private Long idCliente;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private Date fechaExpedicionDocumento;
    private String lugarExpedicionDocumento;
    private Date fechaCreacion;
    private Long usuarioId;
    private Set<TelefonoDto> telefonos = new HashSet<>();
    private Set<DireccionDto> direcciones = new HashSet<>();
    private Set<CorreoElectronicoDto> correosElectronicos = new HashSet<>();

    public ClientesDto() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public String getLugarExpedicionDocumento() {
        return lugarExpedicionDocumento;
    }

    public void setLugarExpedicionDocumento(String lugarExpedicionDocumento) {
        this.lugarExpedicionDocumento = lugarExpedicionDocumento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Set<TelefonoDto> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<TelefonoDto> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<DireccionDto> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<DireccionDto> direcciones) {
        this.direcciones = direcciones;
    }

    public Set<CorreoElectronicoDto> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(Set<CorreoElectronicoDto> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
    }

}
