package com.example.agenda_hexagonal.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agenda_hexagonal.infrastructure.adapters.entities.ContatoEntity;

@Repository
public interface ContatoJpaRepository extends JpaRepository<ContatoEntity, Long> {
}
