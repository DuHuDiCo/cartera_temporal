
package com.cartera_temp.cartera_temp.ModelsClients;

import java.util.Objects;


public class PermisosUsuario {
   
    private Long idPermiso;
    
    private String permiso;
   

    public PermisosUsuario() {
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

   

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return "Permisos{" + "idPermiso=" + idPermiso + ", permiso=" + permiso + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idPermiso);
        hash = 97 * hash + Objects.hashCode(this.permiso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermisosUsuario other = (PermisosUsuario) obj;
        if (!Objects.equals(this.permiso, other.permiso)) {
            return false;
        }
        if (!Objects.equals(this.idPermiso, other.idPermiso)) {
            return false;
        }
        return true;
    }

    
    
    
    
    

}
