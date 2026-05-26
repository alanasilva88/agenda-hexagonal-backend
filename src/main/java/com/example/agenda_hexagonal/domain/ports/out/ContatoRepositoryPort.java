package com.example.agenda_hexagonal.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.example.agenda_hexagonal.domain.model.Contato;

public interface ContatoRepositoryPort {
    Contato salvar(Contato contato);
    Optional<Contato> encontrarPorId(Long id);
    List<Contato> encontrarTodos();
    Contato atualizar(Contato contato);
    void deletar(Long id);
}