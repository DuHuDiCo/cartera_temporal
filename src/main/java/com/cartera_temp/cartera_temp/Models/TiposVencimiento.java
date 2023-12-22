package com.cartera_temp.cartera_temp.Models;

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
@Table(name = "tipos_vencimiento")
public class TiposVencimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_vencimiento")
    private Long idTipoVencimiento;
    
    @Column(name = "tipoVencimiento")
    private String tipoVencimiento;
    
    @OneToMany (mappedBy = "tiposVencimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuentasPorCobrar> cuentasPorCobrar;

    public TiposVencimiento() {
    }
    
    public void agreegarCuentaCobrar(CuentasPorCobrar cpc){
        cuentasPorCobrar.add(cpc);
        cpc.setTiposVencimiento(this);
    }

    public Long getIdTipoVencimiento() {
        return idTipoVencimiento;
    }

    public void setIdTipoVencimiento(Long idTipoVencimiento) {
        this.idTipoVencimiento = idTipoVencimiento;
    }

    public String getTipoVencimiento() {
        return tipoVencimiento;
    }

    public void setTipoVencimiento(String tipoVencimiento) {
        this.tipoVencimiento = tipoVencimiento;
    }

    public List<CuentasPorCobrar> getCuentasPorCobrar() {
        return cuentasPorCobrar;
    }

    public void setCuentasPorCobrar(List<CuentasPorCobrar> cuentasPorCobrar) {
        this.cuentasPorCobrar = cuentasPorCobrar;
    }

}
