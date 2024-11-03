package com.example.test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMatriculaResponse {

    private Integer idDetalle;

    private Integer idCurso;

    private String curso;

    private String estadoCurso;


}
