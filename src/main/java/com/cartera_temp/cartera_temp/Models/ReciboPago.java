
package com.cartera_temp.cartera_temp.Models;

import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recibo_pago")
public class ReciboPago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecibo;
    
    private String numeroRecibo;
    
    private double valorRecibo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaRecibo;
    
    private String ruta;
    
    private String nombreArchivo;
    
  
    private Long  usuarioId;
   
   @OneToMany(mappedBy = "reciboPago", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
    private List<Pagos> pagos = new ArrayList<>();


    public ReciboPago() {
    }

    public void agregarReciboPago(Pagos pago){
        pagos.add(pago);
        pago.setReciboPago(this);
    }
    
    public Long getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Long idRecibo) {
        this.idRecibo = idRecibo;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public double getValorRecibo() {
        return valorRecibo;
    }

    public void setValorRecibo(double valorRecibo) {
        this.valorRecibo = valorRecibo;
    }

    public Date getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

   
   
   

}
