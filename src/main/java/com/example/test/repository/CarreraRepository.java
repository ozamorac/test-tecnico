package com.example.test.repository;

import com.example.test.entity.Carrera;
import com.example.test.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera,Integer>  {

}
