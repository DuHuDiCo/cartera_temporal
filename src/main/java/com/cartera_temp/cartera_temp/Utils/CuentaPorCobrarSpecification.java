package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CuentaPorCobrarSpecification {

    public static Specification<CuentasPorCobrar> filtrarCuentas(FiltroDto filtro) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getBanco() != null) {
                predicates.add(criteriaBuilder.equal(root.get("banco").get("banco"), filtro.getBanco()));
            }

            if (filtro.getClasiJuridica() != null) {
                predicates.add(criteriaBuilder.equal(root.get("clasificacionJuridica"), filtro.getClasiJuridica()));
            }

            if (filtro.getDiasVencidosInicio() != null && filtro.getDiasVencidosFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("diasVencidos"), filtro.getDiasVencidosInicio(), filtro.getDiasVencidosFin()));
            }

            if (filtro.getEdadVencimiento() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tiposVencimiento").get("tipoVencimiento"), filtro.getEdadVencimiento()));
            }

            if (filtro.getFechaCompromisoInicio() != null && filtro.getFechaCompromisoFin() != null) {

                Join<CuentasPorCobrar, Gestiones> gJoin = root.join("gestiones", JoinType.LEFT);
                Join<Gestiones, ClasificacionGestion> cgJoin = gJoin.join("clasificacionGestion", JoinType.LEFT);
                Join<ClasificacionGestion, AcuerdoPago> cpJoin = cgJoin.join("acuerdoPago", JoinType.LEFT);
                
                predicates.add(criteriaBuilder.between(cpJoin.get("fechaCompromiso"), filtro.getFechaCompromisoInicio(), filtro.getFechaCompromisoFin()));
                
            }
            
            if(filtro.getFechaCpcInicio() != null && filtro.getFechaCpcFin() != null){
                predicates.add(criteriaBuilder.between(root.get("fechaCuentaCobrar"), filtro.getFechaCpcInicio(), filtro.getFechaCpcFin()));
            }
            
            if(filtro.getFechaGestionInicio() != null && filtro.getFechaGestionFin() != null){
                predicates.add(criteriaBuilder.between(root.get("gestiones").get("fechaGestion"), filtro.getFechaGestionInicio(), filtro.getFechaGestionFin()));
            }
            
            if(filtro.getSaldoCapitalInicio() != null && filtro.getSaldoCapitalFin() != null){
                predicates.add(criteriaBuilder.between(root.get("totalObligatoria"), filtro.getSaldoCapitalInicio(), filtro.getSaldoCapitalFin()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
        
    }

}
