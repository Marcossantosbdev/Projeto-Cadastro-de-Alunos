package com.example.cadastroalunos.infra.exception;

import com.example.cadastroalunos.dto.DadosErroValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    //Sempre que o @Valid falhar, o Spring lança a exceção MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        //Captura todos os erros de campos que falharam a validação
        var erros = ex.getFieldErrors();

        //Converte a lista de erros do Spring para os DTOs limpo
        var dadosErros = erros.stream().map(DadosErroValidacao::new).toList();

        //Devolve o Status 400 Bad Request com o JSON customizado
        return ResponseEntity.badRequest().body(dadosErros);
    }
}
