package com.example.cursos.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursos.entity.Curso;
import com.example.cursos.service.CursoService;
@RestController 
@RequestMapping ("/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service){
        this.service = service; 
    }

    @GetMapping()
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id){
        Optional<Curso> optional = service.getById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Curso> save(@RequestBody Curso curso){
        Curso savedCurso = service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso){
        Optional<Curso> updatedCurso = service.update(id, curso);
        if(updatedCurso.isPresent()){
            return ResponseEntity.ok(updatedCurso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            boolean deleted = service.delete(id);
            if(deleted){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
}
