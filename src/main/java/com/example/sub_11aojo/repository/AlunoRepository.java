package com.example.sub_11aojo.repository;

import com.example.sub_11aojo.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno, Long> {
    public Aluno findByEmail(String email);
}
