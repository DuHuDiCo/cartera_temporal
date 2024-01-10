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
@Table(name = "condicion_especial")
public class CondicionEspecial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condicion_especial")
    private Long idCondicionEspecial;
    
    @Column(name = "condicion_especial")
    private String condicionEspecial;
    
    @OneToMany (mappedBy = "condicionEspecial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CuentasPorCobrar> cuentasPorCobrar;

    public CondicionEspecial() {
    }

    public void agreegarCuentaCobrar(CuentasPorCobrar cpc){
        cuentasPorCobrar.add(cpc);
        cpc.setCondicionEspecial(this);
    }
    
    public Long getIdCondicionEspecial() {
        return idCondicionEspecial;
    }

    public void setIdCondicionEspecial(Long idCondicionEspecial) {
        this.idCondicionEspecial = idCondicionEspecial;
    }

    public String getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    public List<CuentasPorCobrar> getCuentasPorCobrar() {
        return cuentasPorCobrar;
    }

    public void setCuentasPorCobrar(List<CuentasPorCobrar> cuentasPorCobrar) {
        this.cuentasPorCobrar = cuentasPorCobrar;
    }
    
    
    
}
