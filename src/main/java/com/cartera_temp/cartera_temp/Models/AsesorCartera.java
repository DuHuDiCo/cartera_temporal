package com.cartera_temp.cartera_temp.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asesor_cartera")
public class AsesorCartera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor_cartera")
    private Long idAsesorCartera;

    @Column(name = "asesor")
    private Long usuarioId;

    public AsesorCartera() {
    }

    public Long getIdAsesorCartera() {
        return idAsesorCartera;
    }

    public void setIdAsesorCartera(Long idAsesorCartera) {
        this.idAsesorCartera = idAsesorCartera;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }



  

    
    
    
    

}
