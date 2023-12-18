

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.NombresClasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NombresClasificacionRepository extends JpaRepository<NombresClasificacion, Long>{
    
    NombresClasificacion findByNombreAndTipo(String nombre, String tipo);
    
    NombresClasificacion findFirstByNombre(String nombre);
    
}
