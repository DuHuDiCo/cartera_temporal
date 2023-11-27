package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class Direccion {

    private Long idDireccion;
    private String direccion;
    private String tipoDireccion;
    private boolean isCurrent;
    private Date fechaCreacion;
    private ClientesDto cliente;

    public Direccion() {
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public boolean isIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ClientesDto getCliente() {
        return cliente;
    }

    public void setCliente(ClientesDto cliente) {
        this.cliente = cliente;
    }

    
    
}
