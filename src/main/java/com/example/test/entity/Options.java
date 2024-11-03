package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="options")
public class Options implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer idOption;

    @Column(name="idPregunta")
    private Integer idPregunta;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="tipo")
    private String tipo;

    @Column(name="score")
    private Long score;

    @Column(name="isCorrectAnswer")
    private Boolean isCorrectAnswer;

    @Column(name="estado")
    private String estado;

}
