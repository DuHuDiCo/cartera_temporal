
package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.Banco;
import java.util.List;

public interface BancoService {
    
    
    public Banco guardarBanco(String banco);
    public List<Banco> listarBancos();
    public Banco findBanco(String banco);
    

}
