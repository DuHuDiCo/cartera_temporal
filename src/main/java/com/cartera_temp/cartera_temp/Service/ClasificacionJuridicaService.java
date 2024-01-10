package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import java.util.List;

public interface ClasificacionJuridicaService {

    public ClasificacionJuridica saveClasificacion(ClasificacionJuridica cj);
    
    public List<ClasificacionJuridica> getAllClasificacion();
    
    public ClasificacionJuridica getClasificacionById(Long id);
    
    public ClasificacionJuridica getClasificacionByNombre(String nombre);
    
    public ClasificacionJuridica updateClasificacion(ClasificacionJuridica cj);
    
}
