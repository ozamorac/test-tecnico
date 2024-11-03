package com.example.test.service;

import com.example.test.entity.Curso;
import com.example.test.request.CursoRequest;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> findAllCursos();

    List<Curso> findCursosByAlumno(Long idAlumno);

    Optional<Curso> findCursoById(Long id);

    Curso saveCurso(CursoRequest cursoRequest);

    Curso editCurso(CursoRequest cursoRequest);

    Curso removeCurso(Long id);
}
