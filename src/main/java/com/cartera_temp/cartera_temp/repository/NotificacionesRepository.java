package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    List<Notificaciones> findByDesignatedToAndFechaFinalizacionBetween(Long id, Date fechaInicio, Date fechaFin);

    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasOrderByFechaCreacionAsc(boolean isActive, Long id, String ver);
    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContainingOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String sede);
    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndClienteOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String cliente);

    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(boolean IsActive, Long id, String ver,  Date fechaActual);
    
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String numeroObligacion);
    List<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndClienteContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String cliente);
    List<Notificaciones> findByIsActiveAndDesignatedToAndNumeroObligacionContaining(boolean IsActive, Long id,String numeroObligacion);
    List<Notificaciones> findByIsActiveAndDesignatedToAndClienteContaining(boolean IsActive, Long id,  String cliente);
    
    List<Notificaciones> findByNumeroObligacionAndFechaCreacionGreaterThanEqualAndTipoGestionOrderByFechaCreacionDesc(String obligacion, Date fecha, String tipo);
}
