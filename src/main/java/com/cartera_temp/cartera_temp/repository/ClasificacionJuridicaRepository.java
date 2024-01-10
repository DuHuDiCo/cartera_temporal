package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionJuridicaRepository extends JpaRepository<ClasificacionJuridica, Long>{
    
    ClasificacionJuridica findByClasificacionJuridica(String clasificacionJuridica);
    
}
