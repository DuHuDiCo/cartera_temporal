
package com.cartera_temp.cartera_temp.Dtos;

import com.cartera_temp.cartera_temp.ModelsClients.Usuario;


public class AsesorCarteraResponse {
    
     private Long idAsesorCartera;
     private Usuario usuario;

    public AsesorCarteraResponse() {
    }

    public Long getIdAsesorCartera() {
        return idAsesorCartera;
    }

    public void setIdAsesorCartera(Long idAsesorCartera) {
        this.idAsesorCartera = idAsesorCartera;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     
     
     

}
