package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import java.util.List;

public interface AsesorCarteraService {

    public AsesorCartera guardarAsesor(Long asesor);

    public List<AsesorCartera> listarAsesores();

    public AsesorCartera findAsesor(Long asesor);
    
    public AsesorCartera findAsesorByNombreAsesor(String nombreAsesor);

    
}
