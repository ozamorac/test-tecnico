package com.example.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {

    private Integer idOption;

    @NotNull(message="No puede ser null")
    private String descripcion;

    @NotNull(message="No puede ser null")
    private String tipo;

    @NotNull(message="No puede ser null")
    private Long score;

    private Boolean isCorrectAnswer;

    @NotNull(message="No puede ser null")
    private String estado;

}
