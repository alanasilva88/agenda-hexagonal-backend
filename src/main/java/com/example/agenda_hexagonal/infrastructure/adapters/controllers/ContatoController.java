package com.example.agenda_hexagonal.infrastructure.adapters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda_hexagonal.domain.model.Contato;
import com.example.agenda_hexagonal.domain.ports.in.ContatoUseCase;

@RestController
@RequestMapping("/api/contatos") 
@CrossOrigin(origins = "*" ) 
public class ContatoController {

    private final ContatoUseCase contatoUseCase;

    public ContatoController(ContatoUseCase contatoUseCase) {
        this.contatoUseCase = contatoUseCase;
    }

    @PostMapping
    public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
        Contato novoContato = contatoUseCase.criarContato(contato);
        return new ResponseEntity<>(novoContato, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listar() {
        List<Contato> contatos = contatoUseCase.listarContatos();
        return ResponseEntity.ok(contatos); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        return contatoUseCase.obterContatoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody Contato contato) {
        try {
            Contato contatoAtualizado = contatoUseCase.atualizarContato(id, contato);
            return ResponseEntity.ok(contatoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            contatoUseCase.deletarContato(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}