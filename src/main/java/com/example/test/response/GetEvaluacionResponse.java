package com.example.test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEvaluacionResponse {

    private AlumnoResponse alumno;

    private CursoResponse curso;

    private LeccionResponse leccion;

    private String estado;

    private List<PreguntaResponse> preguntas;

}
