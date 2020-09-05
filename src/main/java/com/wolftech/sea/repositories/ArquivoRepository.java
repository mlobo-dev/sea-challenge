package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.Arquivo;
import com.wolftech.sea.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}