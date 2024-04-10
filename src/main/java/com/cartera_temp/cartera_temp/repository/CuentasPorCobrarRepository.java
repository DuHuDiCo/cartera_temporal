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
public interface CuentasPorCobrarRepository extends JpaRepository<CuentasPorCobrar, Long>, JpaSpecificationExecutor<CuentasPorCobrar> {
    
    
    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.*FROM `cuentas_por_cobrar` "
            + "JOIN  banco ON cuentas_por_cobrar.banco_id = banco.id_banco "
            + "JOIN tipos_vencimiento ON cuentas_por_cobrar.tipo_vencimiento_id = tipos_vencimiento.id_tipo_vencimiento "
            + "JOIN sede ON cuentas_por_cobrar.sede_id = sede.id_sede "
            + "JOIN clasificacion_juridica ON cuentas_por_cobrar.clasificacion_juridica_id = clasificacion_juridica.id_clasificacion_juridica "
            + "JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar "
            + "JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion "
            + "JOIN tarea ON tarea.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion"
            + " JOIN nombres_clasificacion ON tarea.tipo_clasificacion_id = nombres_clasificacion.id_nombre_clasificacion "
            + "WHERE gestiones.fecha_gestion = (SELECT MAX(fecha_gestion) from gestiones as g WHERE g.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar) AND nombres_clasificacion.id_nombre_clasificacion = :idNombre AND "
            + "(:bancos IS NULL  OR :bancos  IS EMPTY  OR banco.banco IN :bancos) AND (:vencimientos IS NULL OR  :vencimientos IS EMPTY OR tipos_vencimiento.tipo_vencimiento IN :vencimientos) AND (:sedes IS NULL OR :sedes IS EMPTY OR sede.sede IN :sedes) AND "
            + "(:juridicas IS NULL OR :juridicas IS EMPTY  OR clasificacion_juridica.clasificacion_juridica IN :juridicas) AND (:diasVencidos IS NULL OR  IS EMPTY  OR cuentas_por_cobrar.dias_vencidos BETWEEN :diasVencidos[0] AND :diasVencidos[1]) "
            + "ORDER BY dias_vencidos DESC",
            countQuery = "SELECT COUNT(DISTINCT cuentas_por_cobrar.*) FROM `cuentas_por_cobrar` "
            + "JOIN  banco ON cuentas_por_cobrar.banco_id = banco.id_banco "
            + "JOIN tipos_vencimiento ON cuentas_por_cobrar.tipo_vencimiento_id = tipos_vencimiento.id_tipo_vencimiento "
            + "JOIN sede ON cuentas_por_cobrar.sede_id = sede.id_sede "
            + "JOIN clasificacion_juridica ON cuentas_por_cobrar.clasificacion_juridica_id = clasificacion_juridica.id_clasificacion_juridica "
            + "JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar "
            + "JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion "
            + "JOIN tarea ON tarea.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion"
            + " JOIN nombres_clasificacion ON tarea.tipo_clasificacion_id = nombres_clasificacion.id_nombre_clasificacion "
            + "WHERE gestiones.fecha_gestion = (SELECT MAX(fecha_gestion) from gestiones as g WHERE g.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar) AND nombres_clasificacion.id_nombre_clasificacion = :idNombre "
            + "(:bancos IS NULL  OR :bancos  IS EMPTY  OR banco.banco IN :bancos) AND (:vencimientos IS NULL OR  :vencimientos IS EMPTY OR tipos_vencimiento.tipo_vencimiento IN :vencimientos) AND (:sedes IS NULL OR :sedes IS EMPTY OR sede.sede IN :sedes)"
            + "(:juridicas IS NULL OR :juridicas IS EMPTY  OR clasificacion_juridica.clasificacion_juridica IN :juridicas) AND (:diasVencidos IS NULL OR  IS EMPTY  OR cuentas_por_cobrar.dias_vencidos BETWEEN :diasVencidos[0] AND :diasVencidos[1]) "
            + "ORDER BY dias_vencidos DESC",
            nativeQuery = true)
    Page<CuentasPorCobrar> obtenerTareasFiltro(@Param("idNombre") Long id, @Param("bancos") List<String> bancos, @Param("vencimientos") List<String> vencimientos
            ,@Param("sedes") List<String> sedes, @Param("juridicas") List<String> juridicas , @Param("diasVencidos") int[]diasVencidos,Pageable pageable);
    
    
    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM `cuentas_por_cobrar` JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN tarea ON tarea.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion JOIN nombres_clasificacion ON tarea.tipo_clasificacion_id = nombres_clasificacion.id_nombre_clasificacion WHERE gestiones.fecha_gestion BETWEEN :fechaInicio AND :fechaFin AND nombres_clasificacion.id_nombre_clasificacion = :idNombre ORDER BY dias_vencidos DESC", 
            countQuery ="SELECT COUNT( DISTINCT cuentas_por_cobrar.id_cuenta_por_cobrar) FROM `cuentas_por_cobrar` JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN tarea ON tarea.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion JOIN nombres_clasificacion ON tarea.tipo_clasificacion_id = nombres_clasificacion.id_nombre_clasificacion WHERE gestiones.fecha_gestion BETWEEN :fechaInicio AND :fechaFin AND nombres_clasificacion.id_nombre_clasificacion = :idNombre ORDER BY dias_vencidos DESC",nativeQuery = true)
    Page<CuentasPorCobrar> tareas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin, @Param("idNombre") Long id, Pageable pageable);
    

    @Query(value = "SELECT * FROM `cuentas_por_cobrar` WHERE asesor_cartera_id = :id_asesor ORDER BY dias_vencidos DESC",
            countQuery = "SELECT COUNT(*) FROM `cuentas_por_cobrar` WHERE asesor_cartera_id = :id_asesor ORDER BY dias_vencidos DESC", nativeQuery = true)
    Page<CuentasPorCobrar> findByAsesorOrderByDiasVencidosDesc(@Param("id_asesor") Long idAsesor, Pageable pageable);

    @Query(value = "SELECT * FROM `cuentas_por_cobrar`  ORDER BY dias_vencidos DESC",
            countQuery = "SELECT COUNT(*) FROM `cuentas_por_cobrar` ORDER BY dias_vencidos DESC", nativeQuery = true)
    Page<CuentasPorCobrar> findByAll(Pageable pageable);

    CuentasPorCobrar findByNumeroObligacion(String obligacion);

    List<CuentasPorCobrar> findByDocumentoCliente(String cliente);

    List<CuentasPorCobrar> findByNumeroObligacionContaining(String obligacion);

    @Query(value = "SELECT * FROM cuentas_por_cobrar LIMIT 1", nativeQuery = true)
    CuentasPorCobrar isEmpty();

    @Query(value = "ALTER TABLE cuentas_por_cobrar AUTO_INCREMENT = 1", nativeQuery = true)
    void reinicarIds();

    @Query(value = "SELECT * FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion  WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND gestiones.id_asesor = :id_asesor",
            countQuery = "SELECT COUNT(*) FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion   WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND gestiones.id_asesor = :id_asesor", nativeQuery = true)
    Page<CuentasPorCobrar> obtenerCuentasByFechaCompromiso(@Param("fecha") Date fecha, @Param("id_asesor") Long idAsesor, Pageable pageable);

    List<CuentasPorCobrar> findByAsesor(AsesorCartera asesor);

    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor AND cuentas_por_cobrar.mora_obligatoria > 0", nativeQuery = true)
    List<CuentasPorCobrar> gestionesAsignadasByAsesorCount(@Param("idAsesor") Long idAsesor);

    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor", nativeQuery = true)
    List<CuentasPorCobrar> gestionesAsignadasByAsesorCountTotal(@Param("idAsesor") Long idAsesor);

    
    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor AND (SELECT MAX(gestiones.fecha_gestion) FROM gestiones WHERE gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar) < :fechaInicial AND cuentas_por_cobrar.mora_obligatoria > 0", nativeQuery = true)
    List<CuentasPorCobrar> gestionesSinGestion(@Param("idAsesor") Long idAsesor, @Param("fechaInicial") Date fechaIncial);

    @Query(value = "SELECT DISTINCT sede.sede FROM `cuentas_por_cobrar` INNER JOIN sede ON cuentas_por_cobrar.sede_id = sede.id_sede WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY sede.sede ASC", nativeQuery = true)
    List<String> sedesByUsuario(@Param("idAsesor") Long idAsesor);

    @Query(value = "SELECT DISTINCT tipos_vencimiento.tipo_vencimiento FROM `cuentas_por_cobrar` INNER JOIN tipos_vencimiento ON cuentas_por_cobrar.tipo_vencimiento_id = tipos_vencimiento.id_tipo_vencimiento WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY tipos_vencimiento.tipo_vencimiento ASC", nativeQuery = true)
    List<String> vencimientosByUsuario(@Param("idAsesor") Long idAsesor);

    @Query(value = "SELECT DISTINCT clasificacion_juridica.clasificacion_juridica FROM `cuentas_por_cobrar` INNER JOIN clasificacion_juridica ON cuentas_por_cobrar.clasificacion_juridica_id = clasificacion_juridica.id_clasificacion_juridica WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY clasificacion_juridica.clasificacion_juridica ASC", nativeQuery = true)
    List<String> clasificacionJuridicaByUsuario(@Param("idAsesor") Long idAsesor );
    
     @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar WHERE gestiones.fecha_gestion BETWEEN :fechaInicial AND :fechaFin AND cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND cuentas_por_cobrar.mora_obligatoria > 0", nativeQuery = true)
    List<CuentasPorCobrar> gestionesByAsesor(@Param("fechaInicial") Date fechaIncial, @Param("fechaFin") Date fechaFin,@Param("id_asesor")long idAsesor);
    
    
    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion AND acuerdo_pago.fecha_acuerdo BETWEEN :fechaInicial AND :fechaFin",
           nativeQuery = true)
    List<CuentasPorCobrar> acuerdosPagoRealizados(  @Param("id_asesor")long idAsesor,@Param("clasificacion") String clasificacion,@Param("fechaInicial") Date fechaInicial,@Param("fechaFin") Date fechaFin );
    
    @Query(value = "SELECT DISTINCT cuentas_por_cobrar.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion AND acuerdo_pago.fecha_acuerdo BETWEEN :fechaInicial AND :fechaFin AND acuerdo_pago.is_active = true",
           nativeQuery = true)
    List<CuentasPorCobrar> acuerdoPagoActivos(@Param("id_asesor")long idAsesor,@Param("clasificacion") String clasificacion,@Param("fechaInicial") Date fechaInicial,@Param("fechaFin") Date fechaFin);
    
    
    
}
