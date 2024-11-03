package com.example.test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaResponse {

    private Integer idPregunta;

    private Integer idLeccion;

    private String descripcion;

    private String tipo;

    private Integer puntaje;

    private String estado;

    private List<OptionResponse> opciones;

}
