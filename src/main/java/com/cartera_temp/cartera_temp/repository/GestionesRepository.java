package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GestionesRepository extends JpaRepository<Gestiones, Long>{
    
    Gestiones findTopByNumeroObligacionOrderByFechaGestionDesc(String obligacion);
    
    List<Gestiones> findByNumeroObligacionOrderByFechaGestionDesc(String obligacion);
    
   
    
    List<Gestiones> findByAsesorCartera(AsesorCartera asesor);
    
    Gestiones findFirstByNumeroObligacionAndFechaGestion(String obligacion, Date fecha);
    
}
