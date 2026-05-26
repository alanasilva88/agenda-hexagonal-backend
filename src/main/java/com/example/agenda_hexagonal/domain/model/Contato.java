package com.example.agenda_hexagonal.domain.model;

public class Contato {
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public Contato() {}
 
    // Construtor completo
    public Contato(Long id, String nome, String telefone, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato não pode ser vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { 
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato não pode ser vazio.");
        }
        this.nome = nome; 
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}