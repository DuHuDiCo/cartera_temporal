package com.cartera_temp.cartera_temp.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sede")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede")
    private Long idSede;

    @Column(name = "sede", length = 20)
    @NotNull
    @NotBlank
    private String sede;
    
    @Column(name = "telefono_sede", length = 15)
    @NotNull
    @NotBlank
    private String telefonoSede;
    
    @Column(name = "nombre_comercial_sede")
    @NotNull
    @NotBlank
    private String nombreComercialSede;
    
    
    @Column(name = "direccion_sede", length = 15)
    @NotNull
    @NotBlank
    private String direccionSede;

    public Sede() {
    }

    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getTelefonoSede() {
        return telefonoSede;
    }

    public void setTelefonoSede(String telefonoSede) {
        this.telefonoSede = telefonoSede;
    }

    public String getNombreComercialSede() {
        return nombreComercialSede;
    }

    public void setNombreComercialSede(String nombreComercialSede) {
        this.nombreComercialSede = nombreComercialSede;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }
    
    
    

}
