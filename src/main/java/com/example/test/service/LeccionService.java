package com.example.test.service;

import com.example.test.entity.Curso;
import com.example.test.entity.Leccion;
import com.example.test.request.CursoRequest;
import com.example.test.request.LeccionRequest;

import java.util.List;
import java.util.Optional;

public interface LeccionService {

    List<Leccion> findAllLecciones();

    Optional<Leccion> findLeccionById(Long id);

    Leccion saveLeccion(LeccionRequest leccionRequest);

    Leccion editLeccion(LeccionRequest leccionRequest);

    Leccion removeLeccion(Long id);
}
