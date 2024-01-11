package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.CondicionEspecial;
import java.util.List;

public interface CondicionEspecialService {
    
    public CondicionEspecial saveCondicion(CondicionEspecial ce);
    
    public List<CondicionEspecial> getAllCondicion();
    
    public CondicionEspecial getCondicionById(Long id);
    
    public CondicionEspecial getCondicionByNombre(String nombre);
    
    public CondicionEspecial updateCondicion(CondicionEspecial ce);

}
