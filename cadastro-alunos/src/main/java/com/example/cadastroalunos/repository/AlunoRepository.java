package com.example.cadastroalunos.repository;

import com.example.cadastroalunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}