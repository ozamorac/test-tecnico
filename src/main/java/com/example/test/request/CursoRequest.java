package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequest {

    private Long idCurso;

    @NotNull(message="No puede ser null")
    private String nombre;

    @NotNull(message="No puede ser null")
    private Integer ciclo;

    @NotNull(message="No puede ser null")
    private Long idCarrera;

    @NotNull(message="No puede ser null")
    private Integer anio;

    @NotNull(message="No puede ser null")
    private String estado;

}
