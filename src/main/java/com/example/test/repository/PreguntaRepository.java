package com.example.test.repository;

import com.example.test.entity.Leccion;
import com.example.test.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta,Integer>  {

    @Query("select p from Pregunta p where p.estado = 'ACTIVO'")
    List<Pregunta> findAllPreguntasActivos();

    @Query("select p from Pregunta p where p.idLeccion = ?1 and p.estado = 'ACTIVO'")
    List<Pregunta> findAllPreguntasbyLeccion(Integer idLeccion);
}
