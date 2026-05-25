package com.example.cadastroalunos.controller;

import com.example.cadastroalunos.dto.DadosCadastrarAluno;
import com.example.cadastroalunos.dto.DadosAtualizarAluno;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    //1. Listar TODOS
    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        List<Aluno> lista = repository.findAll();
        return ResponseEntity.ok().body(lista);
    }

    //1.2 Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        var aluno = repository.findById(id);

        //Caso encontre o aluno, deve retornar 20OK, se não, retorna 404 Not Found.
        return aluno.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //2. Cadastrar utilizando o DTO e construtor
    @PostMapping
    @Transactional
    //Controle de erros utilizando '?' para Spring aceitar retornar Aluno(Sucesso) ou uma String(erro)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastrarAluno dadosAluno) {

        //1. Escudo de validação (Impedem o puloo de ID no MySQL)
        if (repository.existsByMatricula(dadosAluno.matriculaAluno())){
            return ResponseEntity.badRequest().body("Erro: essa matrícula já está cadastrada!");
        }
        if (repository.existsByEmail(dadosAluno.emailAluno())){
            return ResponseEntity.badRequest().body("Erro: este email já está cadastrado!");
        }
        if (repository.existsByTelefone(dadosAluno.telefoneAluno())){
            return ResponseEntity.badRequest().body("Erro: Este número de Telefone já está cadastrado!");
        }

        //2. Só será executado se passar pelas validações acima
        //Utiliza o construtor inteligente da entidade Aluno.
        Aluno aluno = new Aluno(dadosAluno);
        repository.save(aluno);

        return ResponseEntity.ok().body(aluno);
    }

    //3. Atualizar utilizando mesma base da função cadastrar
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                           @RequestBody @Valid DadosAtualizarAluno dadosAluno) {

        var alunoOptional = repository.findById(id);
        if (alunoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Aluno não encontrado!");
        }
        //Se achou, extrai o aluno de dentro do Optional
        Aluno aluno = alunoOptional.get();

        //Executa as verificações de dados Null
        aluno.atualizarAluno(dadosAluno);

        //Por utilizar o Transactional é desnecessário utilizar repository.save(aluno).
        //O JPA detecta a mudança e atualiza o banco sozinho ao final do metodo.
        return ResponseEntity.ok(aluno);
    }

    //4. Deletar
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id))  {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); //Deve retornar Status 204 No Content
    }
}
