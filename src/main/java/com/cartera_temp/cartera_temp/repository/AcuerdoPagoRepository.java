package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AcuerdoPagoRepository extends JpaRepository<AcuerdoPago, Long>{
    
}
