package com.example.cadastroalunos.dto;

public record DadosAtualizarAluno(
        Long id, // Necessário para identificar qual aluno o banco vai atualizar
        String nomeAluno,
        String emailAluno,
        String cursoAluno,
        String telefoneAluno,
        String enderecoAluno
) {
}
