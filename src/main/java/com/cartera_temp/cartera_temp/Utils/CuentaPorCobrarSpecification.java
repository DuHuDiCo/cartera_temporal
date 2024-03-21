package com.cartera_temp.cartera_temp.Utils;

import GestionesDataDto.enums.TipoClasificacion;
import com.cartera_temp.cartera_temp.Dtos.ClasificacionGestionFiltro;
import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.NombresClasificacion;
import com.cartera_temp.cartera_temp.Models.Nota;
import com.cartera_temp.cartera_temp.Models.Tarea;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

public class CuentaPorCobrarSpecification {

    public static Specification<CuentasPorCobrar> filtrarCuentas(FiltroDto filtro, Long idUsuario) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getBanco() != null && !filtro.getBanco().isEmpty()) {
                predicates.add(root.get("banco").get("banco").in(filtro.getBanco()));
            }

            if (filtro.getSede() != null && !filtro.getSede().isEmpty()) {
                predicates.add(root.get("sede").get("sede").in(filtro.getSede()));
            }

            if (filtro.getClasiJuridica() != null && !filtro.getClasiJuridica().isEmpty()) {
                predicates.add(root.get("clasificacionJuridica").get("clasificacionJuridica").in(filtro.getClasiJuridica()));
            }

            if (filtro.getDiasVencidosInicio() != null && filtro.getDiasVencidosFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("diasVencidos"), filtro.getDiasVencidosInicio(), filtro.getDiasVencidosFin()));
            }

            if (filtro.getEdadVencimiento() != null && !filtro.getEdadVencimiento().isEmpty()) {
                predicates.add(root.get("tiposVencimiento").get("tipoVencimiento").in(filtro.getEdadVencimiento()));
            }

            if (Objects.nonNull(filtro.getClasificacionGestion())) {

                Join<CuentasPorCobrar, Gestiones> gestionesJoin = root.join("gestiones");
                Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionesJoin.join("clasificacionGestion");
                Join<Gestiones, Tarea> tareaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Tarea.class);
                
                predicates.add(criteriaBuilder.isTrue(tareaJoin.get("isActive")));
                predicates.add(criteriaBuilder.equal(tareaJoin.get("nombresClasificacion").get("nombre"), filtro.getClasificacionGestion().getNombreClasificacion()));

               

//                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.ACUERDODEPAGO.getDato())) {
//                    System.out.println("ACUERDO");
//                    Join<Gestiones, AcuerdoPago> acuerdoPagoJoin = criteriaBuilder.treat(clasificacionGestionJoin, AcuerdoPago.class);
//                    Join<AcuerdoPago, NombresClasificacion> nombresClasificacionJoin = acuerdoPagoJoin.join("nombresClasificacion");
//
//                    predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), filtro.getClasificacionGestion().getNombreClasificacion()));
//                    predicates.add(criteriaBuilder.isTrue(acuerdoPagoJoin.get("isActive")));
//                }
//
//                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.NOTA.getDato())) {
//                    System.out.println("NOTA");
//                    Join<Gestiones, Nota> notaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Nota.class);
//                    Join<Nota, NombresClasificacion> nombresClasificacionJoin = notaJoin.join("nombresClasificacion");
//
//                    predicates.add(criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), TipoClasificacion.NOTA.getDato()));
//                    predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), filtro.getClasificacionGestion().getNombreClasificacion()));
//                }
//
//                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.TAREA.getDato())) {
//                    System.out.println("TAREA");
//                    System.out.println(filtro.getClasificacionGestion().getNombreClasificacion());
//                    Join<Gestiones, Tarea> tareaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Tarea.class);
//                    Join<Tarea, NombresClasificacion> nombresClasificacionJoin = tareaJoin.join("nombresClasificacion");
//
//                    predicates.add(criteriaBuilder.isTrue(tareaJoin.get("isActive")));
//                    predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), filtro.getClasificacionGestion().getNombreClasificacion()));
//                }
            }

            if (filtro.getFechaCpcInicio() != null && filtro.getFechaCpcFin() != null) {

                predicates.add(criteriaBuilder.between(root.get("fechaCuentaCobrar"), filtro.getFechaCpcInicio(), filtro.getFechaCpcFin()));

            }

            if (filtro.getFechaGestionInicio() != null && filtro.getFechaGestionFin() != null) {
                Join<CuentasPorCobrar, Gestiones> gJoin = root.join("gestiones", JoinType.LEFT);
                predicates.add(criteriaBuilder.between(gJoin.get("fechaGestion"), filtro.getFechaGestionInicio(), filtro.getFechaGestionFin()));
            }

            if (filtro.getSaldoCapitalInicio() != null && filtro.getSaldoCapitalFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("totalObligatoria"), filtro.getSaldoCapitalInicio(), filtro.getSaldoCapitalFin()));
            }

            if (idUsuario != 0L) {
                predicates.add(criteriaBuilder.equal(root.get("asesor").get("usuarioId"), idUsuario));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }

}
