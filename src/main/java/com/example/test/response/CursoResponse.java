package com.example.test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponse {

    private Long idCurso;

    private String nombre;

    private Integer ciclo;

    private CarreraResponse carrera;

    private Integer anio;
}
