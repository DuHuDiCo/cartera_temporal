

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long>{
    
    Sede findBySede(String sede);

}
