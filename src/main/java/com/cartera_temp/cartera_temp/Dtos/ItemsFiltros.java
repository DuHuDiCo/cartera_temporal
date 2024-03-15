
package com.cartera_temp.cartera_temp.Dtos;

import java.util.ArrayList;
import java.util.List;

public class ItemsFiltros {
    
    
    private List<String> sedes = new ArrayList<>();
    private List<String> vencimientos = new ArrayList<>();
    private List<String> clasificacionJuridica  = new ArrayList<>();

    public ItemsFiltros() {
    }

    public List<String> getSedes() {
        return sedes;
    }

    public void setSedes(List<String> sedes) {
        this.sedes = sedes;
    }

    public List<String> getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(List<String> vencimientos) {
        this.vencimientos = vencimientos;
    }

    public List<String> getClasificacionJuridica() {
        return clasificacionJuridica;
    }

    public void setClasificacionJuridica(List<String> clasificacionJuridica) {
        this.clasificacionJuridica = clasificacionJuridica;
    }
    
    
    
    

}
