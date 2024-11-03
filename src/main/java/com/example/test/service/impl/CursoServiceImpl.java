package com.example.test.service.impl;

import com.example.test.entity.Alumno;
import com.example.test.entity.Curso;
import com.example.test.repository.AlumnoRepository;
import com.example.test.repository.CursoRepository;
import com.example.test.request.CursoRequest;
import com.example.test.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAllCursos() {
        return cursoRepository.findAllCursosActivos();
    }

    @Override
    public List<Curso> findCursosByAlumno(Long idAlumno) {
        Optional<Alumno> alumno = Optional.ofNullable(alumnoRepository.findById(idAlumno).orElse(null));
        if (alumno.isPresent()) {
            return cursoRepository.findCursosByAlumno(alumno.get().getIdCarrera());
        }
        return null;
    }

    @Override
    public Optional<Curso> findCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Curso saveCurso(CursoRequest cursoRequest) {

        Curso curso = new Curso();

        curso.setNombre(cursoRequest.getNombre());
        curso.setCiclo(cursoRequest.getCiclo());
        curso.setIdCarrera(cursoRequest.getIdCarrera());
        curso.setAnio(cursoRequest.getAnio());
        curso.setEstado(cursoRequest.getEstado());
        return cursoRepository.save(curso);

    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Curso editCurso(CursoRequest cursoRequest) {
        Curso curso = new Curso();

        curso.setIdCurso(cursoRequest.getIdCurso());
        curso.setNombre(cursoRequest.getNombre());
        curso.setCiclo(cursoRequest.getCiclo());
        curso.setIdCarrera(cursoRequest.getIdCarrera());
        curso.setAnio(cursoRequest.getAnio());
        curso.setEstado(cursoRequest.getEstado());
        return cursoRepository.save(curso);
    }

    @Override
    public Curso removeCurso(Long id) {
        Optional<Curso> curso = Optional.ofNullable(cursoRepository.findById(id).orElse(null));
        if (curso!=null) {
            Curso cursoDelete = new Curso();

            cursoDelete.setIdCurso(curso.get().getIdCurso());
            cursoDelete.setNombre(curso.get().getNombre());
            cursoDelete.setCiclo(curso.get().getCiclo());
            cursoDelete.setIdCarrera(curso.get().getIdCarrera());
            cursoDelete.setAnio(curso.get().getAnio());
            cursoDelete.setEstado("INACTIVO");
            return cursoRepository.save(cursoDelete);
        }
        return null;
    }

}
