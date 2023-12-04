package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Cuotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuotaRepository  extends JpaRepository<Cuotas, Long>{
    
}
