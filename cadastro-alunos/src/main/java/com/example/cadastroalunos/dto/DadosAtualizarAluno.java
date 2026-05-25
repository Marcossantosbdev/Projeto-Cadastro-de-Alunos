package com.example.cadastroalunos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAluno(
        @NotNull(message = "O ID do aluno é obrigatório para a atualização")
        Long id,

        String nomeAluno,

        @Email(message = "O formato do e-mail é inválido") // Se enviar o e-mail, tem que ser válido
        String emailAluno,

        String cursoAluno,
        String telefoneAluno,
        String enderecoAluno
) {
}
