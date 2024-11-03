package com.example.test.service.impl;

import com.example.test.entity.Curso;
import com.example.test.entity.Leccion;
import com.example.test.repository.CursoRepository;
import com.example.test.repository.LeccionRepository;
import com.example.test.request.CursoRequest;
import com.example.test.request.LeccionRequest;
import com.example.test.service.CursoService;
import com.example.test.service.LeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeccionServiceImpl implements LeccionService {

    @Autowired
    private LeccionRepository leccionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Leccion> findAllLecciones() {
        return leccionRepository.findAllLeccionesActivos();
    }

    @Override
    public Optional<Leccion> findLeccionById(Long id) {
        return leccionRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Leccion saveLeccion(LeccionRequest leccionRequest) {

        Leccion leccion = new Leccion();

        leccion.setNombre(leccionRequest.getNombre());
        leccion.setIdCurso(leccionRequest.getIdCurso());
        leccion.setUmbral(leccionRequest.getUmbral());
        leccion.setStatusLeccion(leccionRequest.getStatusLeccion());
        leccion.setEstado(leccionRequest.getEstado());
        return leccionRepository.save(leccion);

    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Leccion editLeccion(LeccionRequest leccionRequest) {
        Leccion leccion = new Leccion();

        leccion.setIdLeccion(leccionRequest.getIdLeccion());
        leccion.setNombre(leccionRequest.getNombre());
        leccion.setIdCurso(leccionRequest.getIdCurso());
        leccion.setUmbral(leccionRequest.getUmbral());
        leccion.setStatusLeccion(leccionRequest.getStatusLeccion());
        leccion.setEstado(leccionRequest.getEstado());
        return leccionRepository.save(leccion);
    }

    @Override
    public Leccion removeLeccion(Long id) {
        Optional<Leccion> leccion = Optional.ofNullable(leccionRepository.findById(id).orElse(null));
        if (leccion!=null) {
            Leccion leccionDelete = new Leccion();

            leccionDelete.setIdLeccion(leccion.get().getIdLeccion());
            leccionDelete.setNombre(leccion.get().getNombre());
            leccionDelete.setIdCurso(leccion.get().getIdCurso());
            leccionDelete.setUmbral(leccion.get().getUmbral());
            leccionDelete.setStatusLeccion(leccion.get().getStatusLeccion());
            leccionDelete.setEstado("INACTIVO");
            return leccionRepository.save(leccionDelete);
        }
        return null;
    }

}
