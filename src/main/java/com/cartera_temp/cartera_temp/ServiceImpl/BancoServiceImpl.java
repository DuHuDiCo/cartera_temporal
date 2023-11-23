
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Service.BancoService;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public class BancoServiceImpl implements BancoService{
    
    private final BancoRepository bancoRepository;

    public BancoServiceImpl(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }
    
    

    @Override
    public Banco guardarBanco(@NotBlank String banco) {
        Banco bank = bancoRepository.findByBanco(banco);
        if(Objects.nonNull(bank)){
            return bank;
        }
        
        bank = new Banco();
        bank.setBanco(banco);
        
        Banco bankSaved = bancoRepository.save(bank);
        
        return  bankSaved;
    }

    @Override
    public List<Banco> listarBancos() {
        List<Banco> bancos = bancoRepository.findAll();
        return bancos;
    }

    @Override
    public Banco findBanco(String banco) {
        Banco bank = bancoRepository.findByBanco(banco);
        if(Objects.isNull(bank)){
            return null;
        }
        return bank;
    }

}
