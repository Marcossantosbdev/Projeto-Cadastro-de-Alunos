package com.example.cadastroalunos.controller;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    //Listar
    @GetMapping
    public List<Aluno> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> buscarPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    //Cadastrar
    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    //Atualizar
    @PutMapping("/{id}")
    @Transactional
    public Aluno atualizar(@PathVariable Long id,
                           @RequestBody Aluno alunoAtualizado) {

        Aluno aluno = repository.findById(id).orElseThrow();

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setMatricula(alunoAtualizado.getMatricula());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setCurso(alunoAtualizado.getCurso());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        aluno.setEndereco(alunoAtualizado.getEndereco());
        aluno.setDataMatricula(alunoAtualizado.getDataMatricula());

        return repository.save(aluno);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}