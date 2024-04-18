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
public interface GestionesRepository extends JpaRepository<Gestiones, Long> {

    Gestiones findTopByNumeroObligacionOrderByFechaGestionDesc(String obligacion);

    List<Gestiones> findByNumeroObligacionOrderByFechaGestionDesc(String obligacion);

    List<Gestiones> findByAsesorCartera(AsesorCartera asesor);

    Gestiones findFirstByNumeroObligacionAndFechaGestion(String obligacion, Date fecha);

    @Query(value = "SELECT DISTINCT gestiones.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar WHERE gestiones.fecha_gestion BETWEEN :fechaInicial AND :fechaFin AND cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND cuentas_por_cobrar.mora_obligatoria > 0", nativeQuery = true)
    List<Gestiones> gestionesByAsesor(@Param("fechaInicial") Date fechaIncial, @Param("fechaFin") Date fechaFin,
            @Param("id_asesor") long idAsesor);

    @Query(value = "SELECT DISTINCT gestiones.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion AND acuerdo_pago.fecha_acuerdo BETWEEN :fechaInicial AND :fechaFin", nativeQuery = true)
    List<Gestiones> acuerdosPagoRealizados(@Param("id_asesor") long idAsesor,
            @Param("clasificacion") String clasificacion, @Param("fechaInicial") Date fechaInicial,
            @Param("fechaFin") Date fechaFin);

    @Query(value = "SELECT DISTINCT gestiones.* FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion WHERE cuentas_por_cobrar.asesor_cartera_id = :id_asesor AND clasificacion_gestion.clasificacion = :clasificacion AND acuerdo_pago.fecha_acuerdo BETWEEN :fechaInicial AND :fechaFin AND acuerdo_pago.is_active = true", nativeQuery = true)
    List<Gestiones> acuerdoPagoActivos(@Param("id_asesor") long idAsesor,
            @Param("clasificacion") String clasificacion, @Param("fechaInicial") Date fechaInicial,
            @Param("fechaFin") Date fechaFin);

}
