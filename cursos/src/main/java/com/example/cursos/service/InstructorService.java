package com.example.cursos.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cursos.entity.Instructor;
import com.example.cursos.repository.InstructorRepository;
@Service 
public class InstructorService {
    private final InstructorRepository repo;

    public InstructorService(InstructorRepository repo){
        this.repo = repo;
    }

    public List<Instructor> getAll() {
        return repo.findAll();
    }
    public Optional<Instructor> getById(Long id){
        return repo.findById(id);
    }
    public Instructor save(Instructor instructor){
        return repo.save(instructor);
    }
    public Optional<Instructor> update(Long id, Instructor instructor){
        Optional<Instructor> optional = repo.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        Instructor instructorDB = optional.get();
        instructorDB.setNombre(instructor.getNombre());
        instructorDB.setCorreo(instructor.getCorreo());
        instructorDB.setEspecialidad(instructor.getEspecialidad());

        return Optional.of(repo.save(instructorDB));
    }
    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
