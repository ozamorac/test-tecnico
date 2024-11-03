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
@Table(name="matricula")
public class Matricula implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer idMatricula;

    @Column(name="idAlumno")
    private Long idAlumno;

    @Column(name="Ciclo")
    private Integer ciclo;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="anio")
    private Integer anio;

    @Column(name="idCarrera")
    private Integer idCarrera;

    @Column(name="estado")
    private String estado;

}
