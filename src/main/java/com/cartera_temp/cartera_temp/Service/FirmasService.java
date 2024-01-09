package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.FirmasDto;
import com.cartera_temp.cartera_temp.Models.Firmas;
import java.util.List;

public interface FirmasService {
    
    public Firmas guardarfirmas (FirmasDto dto);
    
    public List<Firmas> listarFirmas ();
    
    public Firmas findFirmaByUsername(String username);
    
    public Firmas findFirmaById(Long id);
    
    public void deleteFirma(Long id);
    
}
