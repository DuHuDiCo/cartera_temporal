package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    List<Notificaciones> findByDesignatedToAndFechaFinalizacionBetween(Long id, Date fechaInicio, Date fechaFin);

    @Query(value = "SELECT * FROM notificaciones WHERE is_active = :isActive AND id_designated_to = :id AND ver_realizadas =:ver  AND tipo_gestion = 'TAREA' ORDER BY fecha_creacion asc", nativeQuery = true)
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasOrderByFechaCreacionAsc(@Param("isActive") boolean isActive,@Param("id") Long id,@Param("ver") String ver);
    
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContainingAndTipoGestionOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String sede,  String gestion);
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndClienteAndTipoGestionOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String cliente,  String gestion);

    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(boolean IsActive, Long id, String ver,  Date fechaActual);
    
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String numeroObligacion);
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndClienteContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String cliente);
    List<Notificaciones> findByIsActiveAndDesignatedToAndNumeroObligacionContaining(boolean IsActive, Long id,String numeroObligacion);
    List<Notificaciones> findByIsActiveAndDesignatedToAndClienteContaining(boolean IsActive, Long id,  String cliente);
    
    List<Notificaciones> findByNumeroObligacionAndFechaCreacionGreaterThanEqualAndTipoGestionOrderByFechaCreacionDesc(String obligacion, Date fecha, String tipo);
}
