package com.example.test.repository;

import com.example.test.entity.DetalleMatricula;
import com.example.test.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleMatriculaRepository extends JpaRepository<DetalleMatricula,Long>  {

    @Query("select m from DetalleMatricula m where m.idMatricula=?1 and m.estado = 'ACTIVO'")
    List<DetalleMatricula> findDetalleMatricula(Integer idMatricula);

    @Query("select m from DetalleMatricula m where m.idMatricula=?1 and m.idCurso = ?2 and m.estado = 'ACTIVO'")
    DetalleMatricula findByIdAndIdCurso(Integer idMatricula, Integer idCurso);
}
