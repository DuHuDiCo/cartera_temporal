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



            if(!CollectionUtils.isEmpty(filtro.getClasificacionGestion()) && filtro.getClasificacionGestion() != null){
                Join<CuentasPorCobrar, Gestiones> gestionJoin = root.join("gestiones", JoinType.INNER);
                Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionJoin.join("clasificacionGestion", JoinType.INNER);
                
                for (ClasificacionGestionFiltro clasificacionGestionFiltro : filtro.getClasificacionGestion()) {
                    
//                    if(AcuerdoPago.class.isAssignableFrom(clasificacionGestionJoin.getJavaType())){
//                        Join<AcuerdoPago, NombresClasificacion> nombresClasificacionJoin = clasificacionGestionJoin.join("nombreClasificacion");
//                    }
                    
                    if(clasificacionGestionJoin.getJavaType().equals(AcuerdoPago.class)){
                        Join<Gestiones, AcuerdoPago> acuerdoPagoJoin = criteriaBuilder.treat(clasificacionGestionJoin, AcuerdoPago.class);
                        Join<AcuerdoPago, NombresClasificacion> nombresClasificacionJoin = acuerdoPagoJoin.join("nombresClasificacion", JoinType.INNER);
                        
//                        predicates.add(criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), TipoClasificacion.ACUERDODEPAGO.getDato()));
                        predicates.add(criteriaBuilder.isTrue(acuerdoPagoJoin.get("isActive")));
                        predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), clasificacionGestionFiltro.getNombreClasificacion()));
                    }
                    
                    if(clasificacionGestionJoin.getJavaType().equals(Nota.class)){
                        Join<Gestiones, Nota> notaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Nota.class);
                        Join<Nota, NombresClasificacion> nombresClasificacionJoin = notaJoin.join("nombresClasificacion", JoinType.INNER);
                        
//                        predicates.add(criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), TipoClasificacion.NOTA.getDato()));
                       predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), clasificacionGestionFiltro.getNombreClasificacion()));
                    }
                    
                    if(clasificacionGestionJoin.getJavaType().equals(Tarea.class)){
                         Join<Gestiones, Tarea> tareaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Tarea.class);
                         Join<Tarea, NombresClasificacion> nombresClasificacionJoin = tareaJoin.join("nombresClasificacion", JoinType.INNER);
                         
//                         predicates.add(criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), TipoClasificacion.TAREA.getDato()));
                         predicates.add(criteriaBuilder.isTrue(tareaJoin.get("isActive")));
                        predicates.add(criteriaBuilder.equal(nombresClasificacionJoin.get("nombre"), clasificacionGestionFiltro.getNombreClasificacion()));
                    }
                }
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
