package com.example.test.repository;

import com.example.test.entity.Matricula;
import com.example.test.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long>  {

    @Query("select m from Matricula m where m.idAlumno=?1 and m.estado = 'ACTIVO'")
    Matricula findMatriculaByAlumno(Long idAlumno);

}
