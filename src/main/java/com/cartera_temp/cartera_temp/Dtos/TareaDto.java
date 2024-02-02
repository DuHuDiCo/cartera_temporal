package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class TareaDto {

    private String detalleTarea;
    private String fechaFinTarea;
    private boolean isPartOfRecaudo;
  

    public TareaDto() {
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }

    public String getFechaFinTarea() {
        return fechaFinTarea;
    }

    public void setFechaFinTarea(String fechaFinTarea) {
        this.fechaFinTarea = fechaFinTarea;
    }

    public boolean getIsPartOfRecaudo() {
        return isPartOfRecaudo;
    }

    public void setIsPartOfRecaudo(boolean isPartOfRecaudo) {
        this.isPartOfRecaudo = isPartOfRecaudo;
    }
    
    

   
    
    
    
}
