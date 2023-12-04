package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.ClasificacionTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClasificacionTareaRepository extends JpaRepository<ClasificacionTarea, Long>{
    
    ClasificacionTarea findByClasificacionTarea(String clasificacionTarea);
    
}
