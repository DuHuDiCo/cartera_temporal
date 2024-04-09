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
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
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

                boolean active = true;
                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.ACUERDODEPAGO.getDato())) {

                    try {
                        System.out.println(TipoClasificacion.ACUERDODEPAGO.getDato() + "lina 62");
                        query.distinct(true);
                        Join<CuentasPorCobrar, Gestiones> gestionesJoin = root.join("gestiones");
                        Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionesJoin.join("clasificacionGestion");
                        Join<Gestiones, AcuerdoPago> acuerdoPagoJoin = criteriaBuilder.treat(clasificacionGestionJoin, AcuerdoPago.class);

                        predicates.add(criteriaBuilder.and(
                                criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), filtro.getClasificacionGestion().getTipoClasificacion()),
                                criteriaBuilder.between(gestionesJoin.get("fechaGestion"), Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio"), filtro.getFechaGestionFin()),
                                criteriaBuilder.equal(acuerdoPagoJoin.get("isActive"), active)
                        ));
                        System.out.println(Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio").toString());
                        System.out.println(filtro.getFechaGestionFin());
                        System.out.println(filtro.getClasificacionGestion().getId());
                    } catch (ParseException ex) {
                        Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }

                }

                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.NOTA.getDato())) {
                    try {
                        System.out.println(TipoClasificacion.NOTA.getDato() + "lina 81");
                        query.distinct(true);
                        Join<CuentasPorCobrar, Gestiones> gestionesJoin = root.join("gestiones");
                        Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionesJoin.join("clasificacionGestion");
                        Join<Gestiones, Nota> notaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Nota.class);
                        Join<Nota, NombresClasificacion> nombresClasificacionJoin = notaJoin.join("nombresClasificacion");

                        predicates.add(criteriaBuilder.and(
                                criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), filtro.getClasificacionGestion().getTipoClasificacion()),
                                criteriaBuilder.equal(nombresClasificacionJoin.get("idNombreClasificacion"), filtro.getClasificacionGestion().getId()),
                                criteriaBuilder.between(gestionesJoin.get("fechaGestion"), Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio"), filtro.getFechaGestionFin())
                        ));

                        System.out.println(Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio").toString());
                        System.out.println(filtro.getFechaGestionFin());
                        System.out.println(filtro.getClasificacionGestion().getId());
                        System.out.println(filtro.getClasificacionGestion().getTipoClasificacion());
                    } catch (ParseException ex) {
                        Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }

                }

                if (filtro.getClasificacionGestion().getTipoClasificacion().equals(TipoClasificacion.TAREA.getDato())) {
                    try {
                        System.out.println(TipoClasificacion.TAREA.getDato() + "lina 81");
                        query.distinct(true);
//                        Join<CuentasPorCobrar, ClasificacionGestion> clasificacionGestionJoin = root.join("gestiones").join("clasificacionGestion");

                        Subquery<Date> subquery = query.subquery(Date.class);
                        Root<Gestiones> subRoot = subquery.from(Gestiones.class);
                        subquery.select(criteriaBuilder.max(subRoot.get("fechaGestion")).as(Date.class));
                        subquery.where(criteriaBuilder.equal(subRoot.get("cuentasPorCobrar"), root));

                        Join<CuentasPorCobrar, Gestiones> gestionesJoin = root.join("gestiones");
                        Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionesJoin.join("clasificacionGestion");
                        Join<Gestiones, Tarea> tareaJoin = criteriaBuilder.treat(clasificacionGestionJoin, Tarea.class);
                        Join<Tarea, NombresClasificacion> nombresClasificacionJoin = tareaJoin.join("nombresClasificacion");
                        predicates.add(criteriaBuilder.and(
                                //                                criteriaBuilder.equal(root.join("gestiones").join("clasificacionGestion", JoinType.LEFT).join(Tarea.class).get("clasificacion")))
                                //                                criteriaBuilder.between(gestionesJoin.get("fechaGestion"), Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio"), filtro.getFechaGestionFin())
                                criteriaBuilder.equal(root.join("gestiones").get("clasificacionGestion").get("clasificacion"), filtro.getClasificacionGestion().getTipoClasificacion()),
                                //                                criteriaBuilder.equal(criteriaBuilder.treat(clasificacionGestionJoin, Tarea.class).get("nombresClasificacion").get("idNombreClasificacion"), filtro.getClasificacionGestion().getId()),
                                
                                criteriaBuilder.equal(nombresClasificacionJoin.get("idNombreClasificacion"), filtro.getClasificacionGestion().getId())
                        ));
                        
                        predicates.add(criteriaBuilder.equal(root.join("gestiones").get("fechaGestion"), subquery));

                        System.out.println(Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio").toString());
                        System.out.println(filtro.getFechaGestionFin());
                        System.out.println(filtro.getClasificacionGestion().getId());
                        System.out.println(filtro.getClasificacionGestion().getTipoClasificacion());

                    } catch (ParseException ex) {
                        Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }

                }

            }

            if (filtro.getFechaCpcInicio() != null && filtro.getFechaCpcFin() != null) {

                predicates.add(criteriaBuilder.between(root.get("fechaCuentaCobrar"), filtro.getFechaCpcInicio(), filtro.getFechaCpcFin()));

            }

            if (filtro.getFechaGestionInicio() != null && filtro.getFechaGestionFin() != null && Objects.isNull(filtro.getClasificacionGestion())) {
                try {
                    Join<CuentasPorCobrar, Gestiones> gJoin = root.join("gestiones", JoinType.LEFT);
                    predicates.add(criteriaBuilder.between(gJoin.get("fechaGestion"), Functions.fechaConHora(filtro.getFechaGestionInicio(), "inicio"), filtro.getFechaGestionFin()));
                } catch (ParseException ex) {
                    Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (filtro.getSaldoCapitalInicio() != null && filtro.getSaldoCapitalFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("totalObligatoria"), filtro.getSaldoCapitalInicio(), filtro.getSaldoCapitalFin()));
            }

            if (idUsuario != 0L) {
                predicates.add(criteriaBuilder.equal(root.get("asesor").get("usuarioId"), idUsuario));
            }

            query.orderBy(criteriaBuilder.desc(root.get("diasVencidos")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }

}
