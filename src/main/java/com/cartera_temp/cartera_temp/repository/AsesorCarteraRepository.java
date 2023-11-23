

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorCarteraRepository extends JpaRepository<AsesorCartera, Long>{
    
    AsesorCartera findByUsuarioId(Long asesor);

}
