package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query("Select distinct a from Atividade a inner join AtividadeEquipamento ae on ae.funcionario.id = :idFuncionario")
    List<Atividade> findDistinctInAtividadeEquipamentoByFuncionarioId(@Param("idFuncionario") Long idFuncionario);

    boolean existsByNome(String nome);
}