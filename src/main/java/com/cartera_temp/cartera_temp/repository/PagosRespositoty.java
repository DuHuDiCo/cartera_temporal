package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagosRespositoty extends JpaRepository<Pagos, Long>{
    
}
