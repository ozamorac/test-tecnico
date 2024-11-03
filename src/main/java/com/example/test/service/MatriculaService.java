package com.example.test.service;

import com.example.test.entity.Curso;
import com.example.test.entity.Matricula;
import com.example.test.request.CursoRequest;
import com.example.test.request.MatriculaRequest;
import com.example.test.response.MatriculaResponse;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    MatriculaResponse findMatriculaByAlumno(Long idAlumno);

    Matricula saveMatricula(MatriculaRequest matriculaRequest);

}
