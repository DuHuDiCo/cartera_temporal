package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.CondicionEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicionEspecialRepository extends JpaRepository<CondicionEspecial, Long>{
    
    CondicionEspecial findByCondicionEspecial(String condicionEspecial);
    
}
