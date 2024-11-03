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
@Table(name="curso")
public class Curso implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idCurso;

    @Column(name="nombre")
    private String nombre;

    @Column(name="Ciclo")
    private Integer ciclo;

    @Column(name="idCarrera")
    private Long idCarrera;

    @Column(name="anio")
    private Integer anio;

    @Column(name="estado")
    private String estado;

}
