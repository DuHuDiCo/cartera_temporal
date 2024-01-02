package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class CuentaPorCobrarSpecification {

    public static Specification<CuentasPorCobrar> filtrarCuentas(FiltroDto filtro, Long idUsuario) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getBanco() != null && !filtro.getBanco().isEmpty()) {
                predicates.add(root.get("banco").get("banco").in(filtro.getBanco()));
                System.out.println("BANCO");
            }

            if (filtro.getSede() != null && !filtro.getSede().isEmpty()) {
                predicates.add(root.get("sede").get("sede").in(filtro.getSede()));
                System.out.println("SEDE");
            }

            if (filtro.getClasiJuridica() != null && !filtro.getClasiJuridica().isEmpty()) {
                predicates.add(root.get("clasificacionJuridica").in(filtro.getClasiJuridica()));
                System.out.println("JURIDICA");
            }

            if (filtro.getDiasVencidosInicio() != null && filtro.getDiasVencidosFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("diasVencidos"), filtro.getDiasVencidosInicio(), filtro.getDiasVencidosFin()));
                System.out.println("DIAS VENCIDOS");
            }

            if (filtro.getEdadVencimiento() != null && !filtro.getEdadVencimiento().isEmpty()) {
                predicates.add(root.get("tiposVencimiento").get("tipoVencimiento").in(filtro.getEdadVencimiento()));
                System.out.println("EDAD VENCIMIENTO");
            }

            if (filtro.getFechaCompromisoInicio() != null && filtro.getFechaCompromisoFin() != null && filtro.getFechaCompromisoInicio() != "" && filtro.getFechaCompromisoFin() != "") {
                System.out.println(filtro.getFechaCompromisoInicio() + " FECHA INICIO");
                System.out.println(filtro.getFechaCompromisoFin() + " FECHA FIN");
                System.out.println("is active?" + filtro.isIsActive());

                Date fComInicio = null;
                Date fComFin = null;

                try {
                    fComInicio = Functions.stringToDateAndFormat(filtro.getFechaCompromisoInicio());
                } catch (ParseException ex) {
                    Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    fComFin = Functions.stringToDateAndFormat(filtro.getFechaCompromisoFin());
                } catch (ParseException ex) {
                    Logger.getLogger(CuentaPorCobrarSpecification.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("fecha format inicio" + fComInicio);
                System.out.println("fecha format fin" + fComFin);

                // Join con Gestion y ClasificacionGestion
                Join<CuentasPorCobrar, Gestiones> gestionJoin = root.join("gestiones", JoinType.INNER);
                Join<Gestiones, ClasificacionGestion> clasificacionGestionJoin = gestionJoin.join("clasificacionGestion", JoinType.INNER);

                // Join con AcuerdoPago (tratando la relación de herencia)
                Join<Gestiones, AcuerdoPago> acuerdoPagoJoin = criteriaBuilder.treat(clasificacionGestionJoin, AcuerdoPago.class);

                // Agregar condiciones
                predicates.add(criteriaBuilder.equal(clasificacionGestionJoin.get("clasificacion"), "ACUERDO DE PAGO"));
                predicates.add(criteriaBuilder.isTrue(acuerdoPagoJoin.get("isActive")));
                predicates.add(criteriaBuilder.between(acuerdoPagoJoin.get("fechaCompromiso"), fComInicio, fComFin));
                System.out.println("FECHA COMPROMISO");
            }

            if (filtro.getFechaCpcInicio() != null && filtro.getFechaCpcFin() != null) {

                predicates.add(criteriaBuilder.between(root.get("fechaCuentaCobrar"), filtro.getFechaCpcInicio(), filtro.getFechaCpcFin()));
                System.out.println("FECHA CPC");
            }

            if (filtro.getFechaGestionInicio() != null && filtro.getFechaGestionFin() != null) {
                System.out.println(filtro.getFechaGestionInicio());
                System.out.println(filtro.getFechaGestionFin());
                Join<CuentasPorCobrar, Gestiones> gJoin = root.join("gestiones", JoinType.LEFT);
                predicates.add(criteriaBuilder.between(gJoin.get("fechaGestion"), filtro.getFechaGestionInicio(), filtro.getFechaGestionFin()));
                System.out.println("GESTION");
            }

            if (filtro.getSaldoCapitalInicio() != null && filtro.getSaldoCapitalFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("totalObligatoria"), filtro.getSaldoCapitalInicio(), filtro.getSaldoCapitalFin()));
                System.out.println("CAPITAL");
            }

            predicates.add(criteriaBuilder.equal(root.get("asesor").get("usuarioId"), idUsuario));
            
            System.out.println(predicates.toArray(new Predicate[0]).length);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }

}
