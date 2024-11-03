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
@Table(name="pregunta")
public class Pregunta implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer idPregunta;

    @Column(name="idLeccion")
    private Integer idLeccion;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="tipo")
    private String tipo;

    @Column(name="puntaje")
    private Integer puntaje;

    @Column(name="estado")
    private String estado;

}
