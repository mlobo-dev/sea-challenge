package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.AtividadeEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeEquipamentoRepository extends JpaRepository<AtividadeEquipamento, Long> {


    List<AtividadeEquipamento> findAllByFuncionarioIdAndAtividadeId(Long idFuncionario, Long idAtividade);

    void deleteAllByFuncionarioId(Long idUSuario);

}