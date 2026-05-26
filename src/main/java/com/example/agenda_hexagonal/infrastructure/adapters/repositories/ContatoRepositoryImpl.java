package com.example.agenda_hexagonal.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.agenda_hexagonal.domain.model.Contato;
import com.example.agenda_hexagonal.domain.ports.out.ContatoRepositoryPort;
import com.example.agenda_hexagonal.infrastructure.adapters.entities.ContatoEntity;

@Component 
public class ContatoRepositoryImpl implements ContatoRepositoryPort {

    private final ContatoJpaRepository contatoJpaRepository;

    // Injeção de dependência do repositório do Spring
    public ContatoRepositoryImpl(ContatoJpaRepository contatoJpaRepository) {
        this.contatoJpaRepository = contatoJpaRepository;
    }

    @Override
    public Contato salvar(Contato contato) {
        ContatoEntity entity = new ContatoEntity(contato);
        ContatoEntity entitySalva = contatoJpaRepository.save(entity);
        return entitySalva.toDomain();
    }

    @Override
    public Optional<Contato> encontrarPorId(Long id) {
        return contatoJpaRepository.findById(id)
                .map(ContatoEntity::toDomain);
    }

    @Override
    public List<Contato> encontrarTodos() {
        return contatoJpaRepository.findAll()
                .stream()
                .map(ContatoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Contato atualizar(Contato contato) {
        ContatoEntity entity = new ContatoEntity(contato);
        ContatoEntity entityAtualizada = contatoJpaRepository.save(entity);
        return entityAtualizada.toDomain();
    }

    @Override
    public void deletar(Long id) {
        contatoJpaRepository.deleteById(id);
    }
}