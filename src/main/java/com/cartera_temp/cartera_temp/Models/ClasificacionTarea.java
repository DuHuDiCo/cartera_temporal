
package com.cartera_temp.cartera_temp.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClasificacionTarea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion_tarea")
    private Long idClasificacionTarea;
    
    @Column(name = "clasificacion_tarea")
    private String clasificacionTarea;

    public ClasificacionTarea() {
    }

    public Long getIdClasificacionTarea() {
        return idClasificacionTarea;
    }

    public void setIdClasificacionTarea(Long idClasificacionTarea) {
        this.idClasificacionTarea = idClasificacionTarea;
    }

    public String getClasificacionTarea() {
        return clasificacionTarea;
    }

    public void setClasificacionTarea(String clasificacionTarea) {
        this.clasificacionTarea = clasificacionTarea;
    }

    
    
}
