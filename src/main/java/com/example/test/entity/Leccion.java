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
@Table(name="leccion")
public class Leccion implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idLeccion;

    @Column(name="idCurso")
    private Long idCurso;

    @Column(name="nombre")
    private String nombre;

    @Column(name="umbral")
    private Integer umbral;

    @Column(name="statusLeccion")
    private String statusLeccion;

    @Column(name="estado")
    private String estado;

}
