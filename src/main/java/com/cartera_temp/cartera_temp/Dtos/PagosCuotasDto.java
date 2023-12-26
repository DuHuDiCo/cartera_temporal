package com.cartera_temp.cartera_temp.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PagosCuotasDto {

    private String numeroObligacion;
    private List<CuotasDto> cuotasDto = new ArrayList<>();

    public PagosCuotasDto() {
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public List<CuotasDto> getCuotasDto() {
        return cuotasDto;
    }

    public void setCuotasDto(List<CuotasDto> cuotasDto) {
        this.cuotasDto = cuotasDto;
    }
    
    
    
}
