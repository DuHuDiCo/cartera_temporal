package com.cartera_temp.cartera_temp.Dtos;

import java.util.Date;

public class TareaDto {

    private String detalleTarea;
    private Date fechaFinTarea;
  

    public TareaDto() {
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }
    
    public Date getFechaFinTarea() {
        return fechaFinTarea;
    }

    public void setFechaFinTarea(Date fechaFinTarea) {
        this.fechaFinTarea = fechaFinTarea;
    }

   
    
    
    
}
