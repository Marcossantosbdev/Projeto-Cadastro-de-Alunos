package com.example.cadastroalunos.model;

import com.example.cadastroalunos.dto.DadosAtualizarAluno;
import com.example.cadastroalunos.dto.DadosCadastrarAluno;
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

    //Cadastro de alunos
    public Aluno(DadosCadastrarAluno dados) {
    this.nome = dados.nomeAluno();
    this.matricula = dados.matriculaAluno();
    this.email = dados.emailAluno();
    this.curso = dados.cursoAluno();
    this.telefone = dados.telefoneAluno();
    this.endereco = dados.enderecoAluno();
    this.dataMatricula = LocalDate.now();
    }

    //Atualização do cadastro de alunos

    public void atualizarAluno(DadosAtualizarAluno dados) {
        if (dados.nomeAluno() != null) {
            this.nome = dados.nomeAluno();
        }

        if (dados.emailAluno() != null) {
            this.email = dados.emailAluno();
        }

        if (dados.cursoAluno() != null) {
            this.curso = dados.cursoAluno();
        }

        if (dados.telefoneAluno() != null) {
            this.telefone = dados.telefoneAluno();
        }

        if (dados.enderecoAluno() != null) {
            this.endereco = dados.enderecoAluno();
        }
    }
}