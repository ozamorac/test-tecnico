package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeccionRequest {

    private Long idLeccion;

    @NotNull(message="No puede ser null")
    private Long idCurso;

    @NotNull(message="No puede ser null")
    private String nombre;

    @NotNull(message="No puede ser null")
    private Integer umbral;

    private String statusLeccion;

    @NotNull(message="No puede ser null")
    private String estado;

}
