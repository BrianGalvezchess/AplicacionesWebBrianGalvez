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

import com.example.cursos.entity.Instructor;
import com.example.cursos.service.InstructorService;
@RestController 
@RequestMapping ("/instructores")
public class InstructorController {
    private final InstructorService service;

    public InstructorController(InstructorService service){
        this.service = service; 
    }

    @GetMapping()
    public ResponseEntity<List<Instructor>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
        public ResponseEntity<Instructor> getById(@PathVariable Long id){
            Optional<Instructor> optional = service.getById(id);
            if (optional.isPresent()) {
                return ResponseEntity.ok(optional.get());
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping()
        public ResponseEntity<Instructor> save(@RequestBody Instructor instructor){
            Instructor savedInstructor = service.save(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructor);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor instructor){
            Optional<Instructor> updatedInstructor = service.update(id, instructor);
            if(updatedInstructor.isPresent()){
                return ResponseEntity.ok(updatedInstructor.get());
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            boolean deleted = service.delete(id);
            if(deleted){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }

        
    }
