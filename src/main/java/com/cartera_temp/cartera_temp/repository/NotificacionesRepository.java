package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    List<Notificaciones> findByDesignatedToAndFechaFinalizacionBetween(Long id, Date fechaInicio, Date fechaFin);

    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasOrderByFechaCreacionAsc(boolean isActive, Long id, String ver);

    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(boolean IsActive, Long id, String ver,  Date fechaActual);
    
    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String numeroObligacion);
    List<Notificaciones> findAllByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContaining(boolean IsActive, Long id, String ver, String numeroObligacion);
    
    List<Notificaciones> findByNumeroObligacionAndFechaCreacionGreaterThanEqualAndTipoGestionOrderByFechaCreacionDesc(String obligacion, Date fecha, String tipo);
}
