

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CuentasPorCobrarRepository extends JpaRepository<CuentasPorCobrar, Long>, JpaSpecificationExecutor<CuentasPorCobrar>{
    
    Page<CuentasPorCobrar> findByAsesor(AsesorCartera asesor,  Pageable pageable);
    
    CuentasPorCobrar findByNumeroObligacion(String obligacion);
    
    List<CuentasPorCobrar> findByDocumentoCliente(String cliente);
    
    List<CuentasPorCobrar> findByNumeroObligacionContaining(String obligacion);
    
    @Query(value = "SELECT * FROM cuentas_por_cobrar LIMIT 1", nativeQuery = true)
    CuentasPorCobrar isEmpty();
    
    @Query(value = "ALTER TABLE cuentas_por_cobrar AUTO_INCREMENT = 1", nativeQuery = true)
    void reinicarIds();

}
