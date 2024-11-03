package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMatriculaRequest {

    private Long id;

    @NotNull(message="No puede ser null")
    private Integer idCurso;

    @NotNull(message="No puede ser null")
    private String estadoCurso;

    @NotNull(message="No puede ser null")
    private String estado;

}
