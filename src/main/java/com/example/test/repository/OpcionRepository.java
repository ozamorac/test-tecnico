package com.example.test.repository;

import com.example.test.entity.Options;
import com.example.test.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpcionRepository extends JpaRepository<Options,Integer>  {

    @Query("select p from Options p where p.idPregunta= ?1")
    List<Options> findAllOpcionesByPregunta(Integer idPregunta);

}
