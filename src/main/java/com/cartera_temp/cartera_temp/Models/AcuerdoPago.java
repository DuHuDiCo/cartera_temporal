
package com.cartera_temp.cartera_temp.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AcuerdoPago extends Clasificacion {
    
    
    @OneToOne(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private Gestiones gestion;
    
    private String detalle;
    
    
    

}
