

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorCarteraRepository extends JpaRepository<AsesorCartera, Long>{
    
    AsesorCartera findByUsuarioId(Long asesor);
    
    
    
    @Query(value = "ALTER TABLE asesor_cartera AUTO_INCREMENT = 1", nativeQuery = true)
    void reinicarIds();

}
