package com.cartera_temp.cartera_temp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "clasificacion_juridica")
public class ClasificacionJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion_juridica")
    private Long idClasificacionJuridica;
    
    @Column(name = "clasificacion_juridica")
    private String clasificacionJuridica;
    
    @OneToMany (mappedBy = "clasificacionJuridica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CuentasPorCobrar> cuentasPorCobrar;

    public ClasificacionJuridica() {
    }
    
    public void agregarCuentaCobrar(CuentasPorCobrar cpc){
        cuentasPorCobrar.add(cpc);
        cpc.setClasificacionJuridica(this);
    }

    public Long getIdClasificacionJuridica() {
        return idClasificacionJuridica;
    }

    public void setIdClasificacionJuridica(Long idClasificacionJuridica) {
        this.idClasificacionJuridica = idClasificacionJuridica;
    }

    public String getClasificacionJuridica() {
        return clasificacionJuridica;
    }

    public void setClasificacionJuridica(String clasificacionJuridica) {
        this.clasificacionJuridica = clasificacionJuridica;
    }

    public List<CuentasPorCobrar> getCuentasPorCobrar() {
        return cuentasPorCobrar;
    }

    public void setCuentasPorCobrar(List<CuentasPorCobrar> cuentasPorCobrar) {
        this.cuentasPorCobrar = cuentasPorCobrar;
    }
    
    
    
}
