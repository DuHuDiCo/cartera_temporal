
package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.TipoClasificacionGestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClasificacionGestionRepository extends JpaRepository<TipoClasificacionGestion, Long>{

    TipoClasificacionGestion findByClasificacion(String clasificacion);
}
