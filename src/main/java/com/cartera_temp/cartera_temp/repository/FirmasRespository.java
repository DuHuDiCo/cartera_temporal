package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Firmas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmasRespository extends JpaRepository<Firmas, Long>{
    
    Firmas findByUsername(String username);
    
}
