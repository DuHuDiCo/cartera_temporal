

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
    
    @Query(value = "←\n" +
"phpMyAdmin\n" +
"Página de inicioFinalizar sesiónDocumentación de phpMyAdminDocumentaciónAjustes del panel de navegaciónRecargar el panel de navegación\n" +
"RecienteFavoritas\n" +
"Colapsar todosDeshacer enlace en el panel principal\n" +
"Nueva\n" +
"Expandir/ColapsarOpciones de la base de datosadministration-microservice\n" +
"Expandir/ColapsarOpciones de la base de datoscartera-temporal\n" +
"NuevaNueva\n" +
"Expandir/ColapsarEstructuraacuerdo_pago\n" +
"Expandir/ColapsarEstructuraasesor_cartera\n" +
"Expandir/ColapsarEstructurabancoOcultar\n" +
"Expandir/ColapsarEstructuraclasificacion_gestion\n" +
"Expandir/ColapsarEstructuraclasificacion_juridica\n" +
"Expandir/ColapsarEstructuracondicion_especial\n" +
"Expandir/ColapsarEstructuracuentas_por_cobrar\n" +
"Expandir/ColapsarEstructuracuotas\n" +
"Expandir/ColapsarEstructuradiscriminacion\n" +
"Expandir/ColapsarEstructurafirmas\n" +
"Expandir/ColapsarEstructuragestiones\n" +
"Expandir/ColapsarEstructurahibernate_sequence\n" +
"Expandir/ColapsarEstructurahistorico_acuerdos_pago\n" +
"Expandir/ColapsarEstructuranombres_clasificacion\n" +
"Expandir/ColapsarEstructuranota\n" +
"Expandir/ColapsarEstructuranotificaciones\n" +
"Expandir/ColapsarEstructurapagos\n" +
"Expandir/ColapsarEstructurarecibo_pago\n" +
"Expandir/ColapsarEstructurasede\n" +
"Expandir/ColapsarEstructuratarea\n" +
"Expandir/ColapsarEstructuratipos_vencimiento\n" +
"Expandir/ColapsarOpciones de la base de datoscartera_microservice\n" +
"Expandir/ColapsarOpciones de la base de datoschecklist\n" +
"Expandir/ColapsarOpciones de la base de datosclientes-microservice\n" +
"Expandir/ColapsarOpciones de la base de datosclientes-temporal\n" +
"Expandir/ColapsarOpciones de la base de datosconsignaciones-microservice\n" +
"Expandir/ColapsarOpciones de la base de datoscreditos_electrohogar\n" +
"Expandir/ColapsarOpciones de la base de datoselectro_hogar\n" +
"Expandir/ColapsarOpciones de la base de datosinformation_schema\n" +
"Expandir/Colapsar\n" +
" informes\n" +
"Expandir/ColapsarOpciones de la base de datosmysql\n" +
"Expandir/ColapsarOpciones de la base de datosperformance_schema\n" +
"Expandir/ColapsarOpciones de la base de datosphpmyadmin\n" +
"Expandir/ColapsarOpciones de la base de datosrifa_gmj\n" +
"Expandir/ColapsarOpciones de la base de datossys\n" +
"Expandir/ColapsarOpciones de la base de datostelemercadeo\n" +
"Expandir/ColapsarOpciones de la base de datosusers_microservice\n" +
"Expandir/ColapsarOpciones de la base de datosvalidations-microservice\n" +
"Expandir/ColapsarOpciones de la base de datosventas_temporal\n" +
"Servidor: localhost:3306 »Base de datos: cartera-temporal »Tabla: cuentas_por_cobrar\n" +
"Examinar Examinar\n" +
"Estructura Estructura\n" +
"SQL SQL\n" +
"Buscar Buscar\n" +
"Insertar Insertar\n" +
"Exportar Exportar\n" +
"Importar Importar\n" +
"Privilegios Privilegios\n" +
"Operaciones Operaciones\n" +
"Más\n" +
"Pulse en la barra para deslizarse al tope de la página\n" +
"Consola de consultas SQL Consola\n" +
"ascendentedescendenteOrden:Depuración SQLOrden de ejecuciónTiempo necesarioOrdenar por:Consultas grupales\n" +
"Ocurrió un error al obtener información de depuración SQL.\n" +
"FavoritosActualizarAgregar\n" +
"Ningún favorito\n" +
"Agregar favorito\n" +
"Etiqueta: \n" +
" Base de datos de destino: \n" +
" Compartir favorito OK\n" +
"​\n" +
"OpcionesDefinir predeterminado\n" +
"Siempre expandir mensajes de consultas\n" +
"Mostrar histórico de consultas al iniciar\n" +
"Mostrar consulta de navegación actual\n" +
" Ejecute consultas con Enter e introduce la nueva línea con Shift + Enter. Para que esto permanezca, vea la configuración.\n" +
"Cambiar al tema oscuro\n" +
" La selección actual no contiene una columna única. La edición de la grilla y los enlaces de copiado, eliminación y edición no están disponibles. Documentación\n" +
" Mostrando filas 0 - 0 (total de 1, La consulta tardó 0.0067 segundos.)\n" +
"SELECT * FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion inner JOIN asesor_cartera ON cuentas_por_cobrar.asesor_cartera_id = asesor_cartera.id_asesor_cartera WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND asesor_cartera.id_asesor_cartera = :id_asesor",
            countQuery = "SELECT * FROM cuentas_por_cobrar JOIN gestiones ON gestiones.cuenta_cobrar_id = cuentas_por_cobrar.id_cuenta_por_cobrar JOIN clasificacion_gestion ON gestiones.clasificacion_gestion_id = clasificacion_gestion.id_clasificacion_gestion JOIN acuerdo_pago ON acuerdo_pago.id_clasificacion_gestion = clasificacion_gestion.id_clasificacion_gestion inner JOIN asesor_cartera ON cuentas_por_cobrar.asesor_cartera_id = asesor_cartera.id_asesor_cartera WHERE acuerdo_pago.fecha_compromiso <= :fecha AND acuerdo_pago.is_active = true AND asesor_cartera.id_asesor_cartera = :id_asesor", nativeQuery = true)
    Page<CuentasPorCobrar> obtenerCuentasByFechaCompromiso(@Param("fecha")Date fecha, @Param("id_asesor") Long idAsesor, Pageable pageable);

}
