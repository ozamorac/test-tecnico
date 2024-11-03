package com.example.test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {

    private Integer idOption;

    private String descripcion;

    private String tipo;

    private Long score;

    private Boolean isCorrectAnswer;

    private String estado;

}
