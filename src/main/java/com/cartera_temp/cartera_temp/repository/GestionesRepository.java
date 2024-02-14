package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
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
    
    @Query(value = "SELECT COUNT(*) FROM gestiones WHERE fecha_gestion >= :fecha AND id_asesor = :id_asesor", nativeQuery = true)
    int gestionesByAsesor(@Param("fecha") Date fecha, @Param("id_asesor")long idAsesor);
    
    
    @Query(value = "SELECT COUNT(*) FROM gestiones JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE gestiones.fecha_gestion >= :fecha AND gestiones.id_asesor = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion",
           nativeQuery = true)
    int acuerdosPagoRealizados(@Param("fecha") Date fecha, @Param("clasificacion") String clasificacion, @Param("id_asesor")long idAsesor);
    
    @Query(value = "SELECT COUNT(*) FROM gestiones JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE gestiones.fecha_gestion >= :fecha AND gestiones.id_asesor = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion AND acuerdo_pago.is_active = true", nativeQuery = true)
    int acuerdoPagoActivos(@Param("fecha") Date fecha, @Param("clasificacion") String clasificacion, @Param("id_asesor")long idAsesor);
    
    
    List<Gestiones> findByAsesorCartera(AsesorCartera asesor);
    
    Gestiones findFirstByNumeroObligacionAndFechaGestion(String obligacion, Date fecha);
    
}
