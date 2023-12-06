package com.cartera_temp.cartera_temp.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_clasificacion_gestion")
public class TipoClasificacionGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion_gestion")
    private Long idClasificacionGestion;

    @Column(name = "clasificacion")
    private String clasificacion;

    public TipoClasificacionGestion() {
    }

    public Long getIdClasificacionGestion() {
        return idClasificacionGestion;
    }

    public void setIdClasificacionGestion(Long idClasificacionGestion) {
        this.idClasificacionGestion = idClasificacionGestion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    
    

}
