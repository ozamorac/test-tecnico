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
@Table(name="detalle_matricula")
public class DetalleMatricula implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="idMatricula")
    private Integer idMatricula;

    @Column(name="idCurso")
    private Integer idCurso;

    @Column(name="estadoCurso")
    private String estadoCurso;

    @Column(name="estado")
    private String estado;

}
