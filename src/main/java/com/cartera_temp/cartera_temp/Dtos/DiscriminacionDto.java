package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;


public class DiscriminacionDto {

    private double cuota;
    private String observacionDiscriminacion;

    public DiscriminacionDto() {
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
    public String getObservacionDiscriminacion() {
        return observacionDiscriminacion;
    }

    public void setObservacionDiscriminacion(String observacionDiscriminacion) {
        this.observacionDiscriminacion = observacionDiscriminacion;
    }
    
    
    
}
