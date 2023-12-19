package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.HistoricoAcuerdosPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoricoAcuerdoPagoRepository extends JpaRepository<HistoricoAcuerdosPago, Long>{
    
}
