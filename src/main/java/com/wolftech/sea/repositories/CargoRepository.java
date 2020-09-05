package com.wolftech.sea.repositories;

import com.wolftech.sea.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    boolean existsByNome(String anyString);
}