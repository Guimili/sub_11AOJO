package com.example.sub_11aojo.controller;

import com.example.sub_11aojo.entity.Aluno;
import com.example.sub_11aojo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAluno() {
        List<Aluno> allAluno = alunoRepository.findAll();
        return new ResponseEntity<>(allAluno, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Aluno> createNewAluno(@RequestBody Aluno alunoBody) {
        Aluno aluno = alunoRepository.save(alunoBody);
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            alunoRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoBody) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            Aluno oldAluno = aluno.get();
            oldAluno.setNome(alunoBody.getNome());
            oldAluno.setEmail(alunoBody.getEmail());
            alunoRepository.save(oldAluno);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
