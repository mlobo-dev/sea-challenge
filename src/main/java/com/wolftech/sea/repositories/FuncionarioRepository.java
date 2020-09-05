package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.Funcionario;
import com.wolftech.sea.enums.StatusFuncionarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Status;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByCpf(String cpf);

    List<Funcionario> findAllByStatus(StatusFuncionarioEnum status);
}