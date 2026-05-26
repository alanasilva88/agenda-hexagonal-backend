package com.example.agenda_hexagonal.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.example.agenda_hexagonal.domain.model.Contato;

public interface ContatoUseCase {
    Contato criarContato(Contato contato);
    Optional<Contato> obterContatoPorId(Long id);
    List<Contato> listarContatos();
    Contato atualizarContato(Long id, Contato contato);
    void deletarContato(Long id);
}