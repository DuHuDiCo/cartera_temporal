package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposVencimientoRepository extends JpaRepository<TiposVencimiento, Long>{
    
    TiposVencimiento findByTipoVencimiento(String tipoVencimiento);
    
}
