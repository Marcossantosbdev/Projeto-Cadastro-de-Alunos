package com.example.cadastroalunos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastrarAluno(
        @NotBlank(message = "O nome é obrigatório!")
        String nomeAluno,

        @NotBlank(message = "A matrícula é obrigatória")
        String matriculaAluno,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O formato do e-mail é inválido") //Valida se tem '@' e '.com'
        String emailAluno,

        @NotBlank(message = "O curso é obrigatório")
        String cursoAluno,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter o DDD e ter 10 ou 11 dígitos numéricos")
        String telefoneAluno,

        @NotBlank(message = "O endereço é obrigatório")
        String enderecoAluno
) {
}
