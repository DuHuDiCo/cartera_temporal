

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionGestionRepository extends JpaRepository<ClasificacionGestion, Long>{
    
    ClasificacionGestion findByClasificacion(String clasificacion);

}
