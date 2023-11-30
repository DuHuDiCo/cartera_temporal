package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.DiscriminacionDto;
import com.cartera_temp.cartera_temp.Dtos.DiscriminacionResponse;


public interface DiscriminacionService {
    
    public DiscriminacionResponse saveGestion(DiscriminacionDto dto);
    
}
