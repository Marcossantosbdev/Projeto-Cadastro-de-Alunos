package com.example.cadastroalunos.dto;

public record DadosAtualizarAluno(
        Long id, // Necessário para identificar qual aluno o banco vai atualizar
        String nome,
        String email,
        String curso,
        String telefone,
        String endereco
) {
}
