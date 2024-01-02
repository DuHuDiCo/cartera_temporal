package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.Models.ReciboPago;

public class PagosCuotasResponse {

    private String base64;
    private String nombreArchivo;
    private ReciboPago reciboPago;

    public PagosCuotasResponse() {
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    
    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public ReciboPago getReciboPago() {
        return reciboPago;
    }

    public void setReciboPago(ReciboPago reciboPago) {
        this.reciboPago = reciboPago;
    }
    
    
    
}
