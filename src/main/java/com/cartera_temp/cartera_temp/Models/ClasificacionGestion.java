package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToOne;

@Entity
@Table(name = "clasificacion_gestion")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClasificacionGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion_gestion")
    private Long idClasificacionGestion;

    @Column(name = "clasificacion")
    private String clasificacion;

    @OneToOne(mappedBy = "clasificacionGestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Gestiones gestion;


    public ClasificacionGestion() {
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

    public Gestiones getGestion() {
        return gestion;
    }

    public void setGestion(Gestiones gestion) {
        this.gestion = gestion;
    }

    

}
