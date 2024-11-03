package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaRequest  {

    private Integer idPregunta;

    @NotNull(message="No puede ser null")
    private Integer idLeccion;

    @NotNull(message="No puede ser null")
    private String descripcion;

    @NotNull(message="No puede ser null")
    private String tipo;

    @NotNull(message="No puede ser null")
    private Integer puntaje;

    @NotNull(message="No puede ser null")
    private String estado;

    @NotNull(message="No puede ser null")
    private List<OptionRequest> opciones;

}
