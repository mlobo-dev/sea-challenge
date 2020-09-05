package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    Optional<Equipamento> findByNumeroCA(Long numeroCA);

    boolean existsByNumeroCA(Long numeroCA);
}