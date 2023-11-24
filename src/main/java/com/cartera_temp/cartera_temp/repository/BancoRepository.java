

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>{

    Banco findByBanco(String banco);
    
    @Query(value = "ALTER TABLE banco AUTO_INCREMENT = 1", nativeQuery = true)
    void reinicarIds();
}
