package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CuentaPorCobrarSpecification {

    public static Specification<CuentasPorCobrar> filtrarCuentas(FiltroDto filtro, Long idUsuario) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getBanco() != null && !filtro.getBanco().isEmpty()) {
                predicates.add(root.get("banco").get("banco").in(filtro.getBanco()));
                System.out.println("BANCO");
            }
            
            if(filtro.getSede() != null && !filtro.getSede().isEmpty()){
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

            if (filtro.getFechaCompromisoInicio() != null && filtro.getFechaCompromisoFin() != null) {

                Join<CuentasPorCobrar, Gestiones> gJoin = root.join("gestiones", JoinType.LEFT);
                Join<Gestiones, ClasificacionGestion> cgJoin = gJoin.join("clasificacionGestion", JoinType.LEFT);
                Join<ClasificacionGestion, AcuerdoPago> cpJoin = cgJoin.join("acuerdoPago", JoinType.LEFT);
                
                predicates.add(criteriaBuilder.between(cpJoin.get("fechaCompromiso"), filtro.getFechaCompromisoInicio(), filtro.getFechaCompromisoFin()));
                System.out.println("FECHA COMPROMISO");
                
            }
            
            if(filtro.getFechaCpcInicio() != null && filtro.getFechaCpcFin() != null){
                predicates.add(criteriaBuilder.between(root.get("fechaCuentaCobrar"), filtro.getFechaCpcInicio(), filtro.getFechaCpcFin()));
                System.out.println("FECHA CPC");
            }
            
            if(filtro.getFechaGestionInicio() != null && filtro.getFechaGestionFin() != null){
                predicates.add(criteriaBuilder.between(root.get("gestiones").get("fechaGestion"), filtro.getFechaGestionInicio(), filtro.getFechaGestionFin()));
                System.out.println("GESTION");
            }
            
            if(filtro.getSaldoCapitalInicio() != null && filtro.getSaldoCapitalFin() != null){
                predicates.add(criteriaBuilder.between(root.get("totalObligatoria"), filtro.getSaldoCapitalInicio(), filtro.getSaldoCapitalFin()));
                System.out.println("CAPITAL");
            }
            
            predicates.add(criteriaBuilder.equal(root.get("asesor").get("usuarioId"), idUsuario));
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
        
    }

}
