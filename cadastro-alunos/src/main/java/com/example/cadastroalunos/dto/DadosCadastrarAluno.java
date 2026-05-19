package com.example.cadastroalunos.dto;

public record DadosCadastrarAluno(
        String nomeAluno,
        String matriculaAluno,
        String emailAluno,
        String cursoAluno,
        String telefoneAluno,
        String enderecoAluno
) {
}
