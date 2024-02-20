package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    Page<Notificaciones> findByDesignatedToAndFechaFinalizacionBetween(Long id, Date fechaInicio, Date fechaFin, Pageable pageable);

    @Query(value = "SELECT * FROM notificaciones WHERE is_active = :isActive AND id_designated_to = :id AND ver_realizadas =:ver  ORDER BY fecha_creacion asc",
            countQuery = "SELECT COUNT(*) FROM notificaciones WHERE is_active = :isActive AND id_designated_to = :id AND ver_realizadas =:ver  ORDER BY fecha_creacion asc",
            nativeQuery = true)
    Page<Notificaciones> obtenerTodasNotificaciones(@Param("isActive") boolean isActive,@Param("id") Long id,@Param("ver") String ver,Pageable pageable );
    
    Page<Notificaciones> findByIsActiveAndDesignatedToAndVerRealizadasAndTipoGestionOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String gestion,  Pageable pageable);
    
    Page<Notificaciones>findByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContainingAndTipoGestionOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String sede,  String gestion,  Pageable pageable);
    Page<Notificaciones>findByIsActiveAndDesignatedToAndVerRealizadasAndClienteContainingAndTipoGestionOrderByFechaCreacionAsc(boolean isActive, Long id, String ver, String cliente,  String gestion,  Pageable pageable);

    Page<Notificaciones>findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(boolean IsActive, Long id, String ver,  Date fechaActual,  Pageable pageable);
    
    Page<Notificaciones>findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String numeroObligacion,  Pageable pageable);
    Page<Notificaciones>findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndClienteContaining(boolean IsActive, Long id, String ver,  Date fechaActual, String cliente,  Pageable pageable);
    Page<Notificaciones>findByIsActiveAndDesignatedToAndNumeroObligacionContaining(boolean IsActive, Long id,String numeroObligacion,  Pageable pageable);
   Page<Notificaciones> findByIsActiveAndDesignatedToAndClienteContaining(boolean IsActive, Long id,  String cliente, Pageable pageable);
    
   Page<Notificaciones> findByNumeroObligacionAndFechaCreacionGreaterThanEqualAndTipoGestionOrderByFechaCreacionDesc(String obligacion, Date fecha, String tipo,  Pageable pageable);
   List<Notificaciones> findByNumeroObligacionAndFechaCreacionGreaterThanEqualAndTipoGestionOrderByFechaCreacionDesc(String obligacion, Date fecha, String tipo);
}
