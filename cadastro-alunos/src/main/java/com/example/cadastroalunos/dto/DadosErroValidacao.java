package com.example.cadastroalunos.dto;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {
    //Construtor auxiliar para facilitar conversão de erro do Spring para o DTO
    public DadosErroValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
