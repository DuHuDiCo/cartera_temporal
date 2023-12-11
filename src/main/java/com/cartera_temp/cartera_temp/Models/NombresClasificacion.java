
package com.cartera_temp.cartera_temp.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas_por_cobrar")
public class NombresClasificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nombre_clasificacion")
    private Long idNombreClasificacion;
    
    @Column(name = "nombre_clasificacion")
    private String nombre;

    public NombresClasificacion() {
    }

    public Long getIdNombreClasificacion() {
        return idNombreClasificacion;
    }

    public void setIdNombreClasificacion(Long idNombreClasificacion) {
        this.idNombreClasificacion = idNombreClasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    

}
