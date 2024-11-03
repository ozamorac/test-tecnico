package com.example.test.response;

import com.example.test.entity.Alumno;
import com.example.test.request.DetalleMatriculaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaResponse {

    private Integer idMatricula;

    private AlumnoResponse alumno;

    private Integer ciclo;

    private String descripcion;

    private Integer anio;

    private CarreraResponse carrera;

    private String estado;

    private List<DetalleMatriculaResponse> detalleMatricula;

}
