package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaRequest {

    private Long idMatricula;

    @NotNull(message="No puede ser null")
    private Long idAlumno;

    @NotNull(message="No puede ser null")
    private Integer ciclo;

    @NotNull(message="No puede ser null")
    private String descripcion;

    @NotNull(message="No puede ser null")
    private Integer anio;

    @NotNull(message="No puede ser null")
    private Integer idCarrera;

    @NotNull(message="No puede ser null")
    private String estado;

    @NotNull(message="No puede ser null")
    private List<DetalleMatriculaRequest> detalleMatricula;

}
