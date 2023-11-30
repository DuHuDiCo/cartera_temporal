package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.DiscriminacionDto;
import com.cartera_temp.cartera_temp.Dtos.DiscriminacionResponse;
import java.util.List;


public interface DiscriminacionService {
    
    public DiscriminacionResponse saveDiscriminacion(DiscriminacionDto dto);
    
    public List<DiscriminacionResponse> findDiscriminacionesByNumeroObligacion(String numeroObligacion);
    
}
