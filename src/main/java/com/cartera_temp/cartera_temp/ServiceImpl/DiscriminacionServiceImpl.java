package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.DiscriminacionDto;
import com.cartera_temp.cartera_temp.Dtos.DiscriminacionResponse;
import com.cartera_temp.cartera_temp.Service.DiscriminacionService;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class DiscriminacionServiceImpl implements DiscriminacionService{

    @Override
    public DiscriminacionResponse saveDiscriminacion(DiscriminacionDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DiscriminacionResponse> findDiscriminacionesByNumeroObligacion(String numeroObligacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DiscriminacionResponse sendLastDiscriminacion(String numeroObligacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
