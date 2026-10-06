package com.example.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cursos.entity.Curso;
import com.example.cursos.repository.CursoRepository;

@Service 
public class CursoService {
    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }
    public List<Curso> getAll(){
        return repo.findAll();
    }
    public Optional<Curso> getById(Long id){
        return repo.findById(id);
    }
    public Curso save(Curso curso){
        return repo.save(curso);
    }
    public Optional<Curso> update(Long id, Curso curso){
        Optional<Curso> optional = repo.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        Curso cursoDB = optional.get();
        cursoDB.setNombre(curso.getNombre());
        cursoDB.setCodigo(curso.getCodigo());
        cursoDB.setCreditos(curso.getCreditos());
        cursoDB.setPrice(curso.getPrice());

        return Optional.of(repo.save(cursoDB));
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
