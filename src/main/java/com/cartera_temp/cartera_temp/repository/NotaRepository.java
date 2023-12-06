

package com.cartera_temp.cartera_temp.repository;

import com.cartera_temp.cartera_temp.Models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends  JpaRepository<Nota, Long>{

}
