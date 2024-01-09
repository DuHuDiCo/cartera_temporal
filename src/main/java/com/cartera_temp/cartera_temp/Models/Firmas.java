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
@Table(name = "firmas")
public class Firmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_firma")
    private Long idFirma;
    
    @Column(name = "nombreArchivo")
    @NotBlank
    @NotNull
    private String nombreArchivo;
    
    @Column(name = "username")
    @NotBlank
    @NotNull
    private String username;

    public Firmas() {
    }

    public Long getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(Long idFirma) {
        this.idFirma = idFirma;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
