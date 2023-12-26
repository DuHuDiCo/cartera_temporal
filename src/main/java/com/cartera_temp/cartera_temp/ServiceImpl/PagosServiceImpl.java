package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Dtos.CuotasDto;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Pagos;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.PagosService;
import com.cartera_temp.cartera_temp.repository.PagosRespositoty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service
public class PagosServiceImpl implements PagosService{
    
    private final PagosRespositoty pagosRespositoty;
    private final CuentasPorCobrarService cpcs;

    public PagosServiceImpl(PagosRespositoty pagosRespositoty, CuentasPorCobrarService cpcs) {
        this.pagosRespositoty = pagosRespositoty;
        this.cpcs = cpcs;
    }

    

    @Override
    public Pagos guardarPago(PagosCuotasDto dto) {
        
        if(dto.getNumeroObligacion() == "" || dto.getNumeroObligacion() == null || dto.getCuotasDto() == null || dto.getCuotasDto().get(0).getPagosDto() == null){
            return null;
        }
        
        CuentasPorCobrarResponse cpc = cpcs.getCpcByNumeroObligacion(dto.getNumeroObligacion());
        if(Objects.isNull(cpc)){
            return null;
        }
        
        List<Cuotas> cuotas = new ArrayList<>();
        
        for (int i = 0; i < dto.getCuotasDto().size(); i++) {
            
            
            
        }
        
    }
    
}
