package com.example.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cursos.entity.Instructor;
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
}
