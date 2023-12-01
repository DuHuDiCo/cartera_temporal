

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CuentasPorCobrarRepository extends JpaRepository<CuentasPorCobrar, Long>{
    
    Page<CuentasPorCobrar> findByAsesor(AsesorCartera asesor,  Pageable pageable);
    
    CuentasPorCobrar findByNumeroObligacion(String obligacion);
    
    List<CuentasPorCobrar> findByNumeroObligacionContaining(String obligacion);
    
    @Query(value = "SELECT * FROM cuentas_por_cobrar LIMIT 1", nativeQuery = true)
    CuentasPorCobrar isEmpty();
    
    @Query(value = "ALTER TABLE cuentas_por_cobrar AUTO_INCREMENT = 1", nativeQuery = true)
    void reinicarIds();

}
