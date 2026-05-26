package com.example.agenda_hexagonal.application.usecases;

import java.util.List;
import java.util.Optional;

import com.example.agenda_hexagonal.domain.model.Contato;
import com.example.agenda_hexagonal.domain.ports.in.ContatoUseCase;
import com.example.agenda_hexagonal.domain.ports.out.ContatoRepositoryPort;


public class ContatoServiceImpl implements ContatoUseCase {

    private final ContatoRepositoryPort contatoRepository;

    public ContatoServiceImpl(ContatoRepositoryPort contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Override
    public Contato criarContato(Contato contato) {
        return contatoRepository.salvar(contato);
    }

    @Override
    public Optional<Contato> obterContatoPorId(Long id) {
        return contatoRepository.encontrarPorId(id);
    }

    @Override
    public List<Contato> listarContatos() {
        return contatoRepository.encontrarTodos();
    }

    @Override
    public Contato atualizarContato(Long id, Contato contatoAtualizado) {
        return contatoRepository.encontrarPorId(id)
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setEmail(contatoAtualizado.getEmail());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    return contatoRepository.salvar(contato);
                })
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

    @Override
    public void deletarContato(Long id) {
        if (!contatoRepository.encontrarPorId(id).isPresent()) {
            throw new RuntimeException("Contato não encontrado");
        }
        contatoRepository.deletar(id);
    }
}