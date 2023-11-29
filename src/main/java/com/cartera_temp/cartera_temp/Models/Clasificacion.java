package com.cartera_temp.cartera_temp.Models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "clasificacion")
public class Clasificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion")
    private Long idClasificacion;
    
    @Column(name = "tipo_clasificacion")
    private String tipoClasificacion;

    public Clasificacion() {
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getTipoClasificacion() {
        return tipoClasificacion;
    }

    public void setTipoClasificacion(String tipoClasificacion) {
        this.tipoClasificacion = tipoClasificacion;
    }
    
    
    
}
