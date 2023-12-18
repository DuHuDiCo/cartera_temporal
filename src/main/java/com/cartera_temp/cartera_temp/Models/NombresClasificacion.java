package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "nombres_clasificacion")
public class NombresClasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nombre_clasificacion")
    private Long idNombreClasificacion;

    @Column(name = "nombre_clasificacion")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "nombresClasificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AcuerdoPago> acuerdoPagos = new ArrayList<>();

    @OneToMany(mappedBy = "nombresClasificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Tarea> tareas = new ArrayList<>();

    @OneToMany(mappedBy = "nombresClasificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Nota> notas = new ArrayList<>();

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<AcuerdoPago> getAcuerdoPagos() {
        return acuerdoPagos;
    }

    public void setAcuerdoPagos(List<AcuerdoPago> acuerdoPagos) {
        this.acuerdoPagos = acuerdoPagos;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

}
