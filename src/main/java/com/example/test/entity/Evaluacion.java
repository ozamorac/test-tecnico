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
@Table(name="evaluacion")
public class Evaluacion implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idEvaluacion;

    @Column(name="idAlumno")
    private Long idAlumno;

    @Column(name="idCurso")
    private Long idCurso;

    @Column(name="idLeccion")
    private Long idLeccion;

    @Column(name="estado")
    private String estado;

}
