

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionesRepository extends  JpaRepository<Notificaciones, Long>{
    
    
    List<Notificaciones> findByDesignatedTo(Long id);

}
