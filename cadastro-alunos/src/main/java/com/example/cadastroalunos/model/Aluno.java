package com.example.cadastroalunos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;
    private String email;
    private String curso;
    private String telefone;
    private String endereco;
    private LocalDate dataMatricula;
}