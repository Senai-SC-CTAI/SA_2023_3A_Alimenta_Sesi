package com.example.back.model;

import jakarta.persistence.*;


@Entity
@Table(name = "usuario")  // Nome da tabela no banco de dados
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    public String getCpf() {
        return cpf;
    }


    public String getSenha() {
        return senha;
    }
}

