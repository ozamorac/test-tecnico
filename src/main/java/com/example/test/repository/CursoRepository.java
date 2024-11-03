package com.example.test.repository;

import com.example.test.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long>  {

    @Query("select c from Curso c where c.estado = 'ACTIVO'")
    List<Curso> findAllCursosActivos();

    @Query("select c from Curso c where c.idCarrera = ?1 and c.estado = 'ACTIVO'")
    List<Curso> findCursosByAlumno(Long idCarrera);

}
