package com.example.cadastroalunos.repository;

import com.example.cadastroalunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    //Herdando de JpaRepository, o Spring já cria o CRUD completo debaixo dos panos
}