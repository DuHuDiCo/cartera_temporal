package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long>{
    
    Clasificacion findByTipoClasificacion(String tipoClasificacion);
    
}
