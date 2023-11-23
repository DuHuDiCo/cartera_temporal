

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>{

    Banco findByBanco(String banco);
    
}
