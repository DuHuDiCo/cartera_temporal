

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
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
    
    @Query(value = "SELECT * FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion  WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND gestiones.id_asesor = :id_asesor",
            countQuery = "SELECT COUNT(*) FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion   WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND gestiones.id_asesor = :id_asesor", nativeQuery = true)
    Page<CuentasPorCobrar> obtenerCuentasByFechaCompromiso(@Param("fecha")Date fecha, @Param("id_asesor") Long idAsesor, Pageable pageable);

    List<CuentasPorCobrar> findByAsesor(AsesorCartera asesor);
    
    @Query(value = "SELECT COUNT(*) FROM cuentas_por_cobrar WHERE asesor_cartera_id = :idAsesor AND dias_vencidos > 0", nativeQuery = true)
    int gestionesAsignadasByAsesorCount(@Param("idAsesor")Long idAsesor);
    
        @Query(value = "SELECT * FROM cuentas_por_cobrar WHERE asesor_cartera_id = :idAsesor AND dias_vencidos > 0", nativeQuery = true)
    List<CuentasPorCobrar> gestionesAsignadasByAsesor(@Param("idAsesor")Long idAsesor);
    
    @Query(value = "SELECT DISTINCT sede.sede FROM `cuentas_por_cobrar` INNER JOIN sede ON cuentas_por_cobrar.sede_id = sede.id_sede WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY sede.sede ASC", nativeQuery = true)
    List<String> sedesByUsuario(@Param("idAsesor")Long idAsesor);
    
    @Query(value = "SELECT DISTINCT tipos_vencimiento.tipo_vencimiento FROM `cuentas_por_cobrar` INNER JOIN tipos_vencimiento ON cuentas_por_cobrar.tipo_vencimiento_id = tipos_vencimiento.id_tipo_vencimiento WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY tipos_vencimiento.tipo_vencimiento ASC", nativeQuery = true)
    List<String> vencimientosByUsuario(@Param("idAsesor")Long idAsesor);
    
    
    @Query(value = "SELECT DISTINCT clasificacion_juridica.clasificacion_juridica FROM `cuentas_por_cobrar` INNER JOIN clasificacion_juridica ON cuentas_por_cobrar.clasificacion_juridica_id = clasificacion_juridica.id_clasificacion_juridica WHERE cuentas_por_cobrar.asesor_cartera_id = :idAsesor ORDER BY clasificacion_juridica.clasificacion_juridica ASC", nativeQuery = true)
    List<String> clasificacionJuridicaByUsuario(@Param("idAsesor")Long idAsesor);
}
