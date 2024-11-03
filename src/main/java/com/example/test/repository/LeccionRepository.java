package com.example.test.repository;

import com.example.test.entity.Curso;
import com.example.test.entity.Leccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeccionRepository extends JpaRepository<Leccion,Long>  {

    @Query("select l from Leccion l where l.estado = 'ACTIVO'")
    List<Leccion> findAllLeccionesActivos();
}
