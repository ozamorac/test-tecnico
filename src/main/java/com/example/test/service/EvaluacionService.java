package com.example.test.service;

import com.example.test.response.GetEvaluacionResponse;

public interface EvaluacionService {

    GetEvaluacionResponse findEvaluacionByAlumnoAndCursoAndLeccion(Long idAlumno, Long idCurso, Long idLeccion);

}
