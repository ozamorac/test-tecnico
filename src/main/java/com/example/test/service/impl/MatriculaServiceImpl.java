package com.example.test.service.impl;

import com.example.test.entity.*;
import com.example.test.repository.*;
import com.example.test.request.CursoRequest;
import com.example.test.request.MatriculaRequest;
import com.example.test.response.*;
import com.example.test.service.CursoService;
import com.example.test.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DetalleMatriculaRepository detalleMatriculaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public MatriculaResponse findMatriculaByAlumno(Long idAlumno) {
        List<DetalleMatriculaResponse> listaDetalleMatriculaResponse = new ArrayList<DetalleMatriculaResponse>();
        Matricula matricula = matriculaRepository.findMatriculaByAlumno(idAlumno);
        Optional<Alumno> alumno = Optional.ofNullable(alumnoRepository.findById(matricula.getIdAlumno()).orElse(null));
        List<DetalleMatricula> detalle = detalleMatriculaRepository.findDetalleMatricula(matricula.getIdMatricula());
        Optional<Carrera> carrera = Optional.ofNullable(carreraRepository.findById(matricula.getIdCarrera()).orElse(null));
        if (matricula != null && alumno.isPresent() && detalle != null && carrera.isPresent()) {
            detalle.stream().forEach(d -> {
                Optional<Curso> curso = Optional.ofNullable(cursoRepository.findById(Long.valueOf(d.getIdCurso())).orElse(null));
                listaDetalleMatriculaResponse.add(
                                new DetalleMatriculaResponse(d.getId(), d.getIdCurso(), curso.get().getNombre(), d.getEstadoCurso())
                        );
                    }
            );

            MatriculaResponse response = new MatriculaResponse();
            AlumnoResponse alumnoResponse = new AlumnoResponse(alumno.get().getIdAlumno(), alumno.get().getNombre(),
                    alumno.get().getApellido());
            CarreraResponse carreraResponse = new CarreraResponse(carrera.get().getIdCarrera(), carrera.get().getNomCarrera());
            response.setIdMatricula(matricula.getIdMatricula());
            response.setAlumno(alumnoResponse);
            response.setCiclo(matricula.getCiclo());
            response.setDescripcion(matricula.getDescripcion());
            response.setAnio(matricula.getAnio());
            response.setCarrera(carreraResponse);
            response.setEstado(matricula.getEstado());
            response.setDetalleMatricula( listaDetalleMatriculaResponse);
            return response;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Matricula saveMatricula(MatriculaRequest matriculaRequest) {

        Matricula matricula = new Matricula();

        matricula.setIdAlumno(matriculaRequest.getIdAlumno());
        matricula.setCiclo(matriculaRequest.getCiclo());
        matricula.setDescripcion(matriculaRequest.getDescripcion());
        matricula.setAnio(matriculaRequest.getAnio());
        matricula.setIdCarrera(matriculaRequest.getIdCarrera());
        matricula.setEstado(matriculaRequest.getEstado());
        Matricula matriculaSave = matriculaRepository.save(matricula);
        matriculaRequest.getDetalleMatricula().stream().forEach(n -> {
                    DetalleMatricula detalle = new DetalleMatricula();
                    detalle.setIdMatricula(matriculaSave.getIdMatricula());
                    detalle.setIdCurso(n.getIdCurso());
                    detalle.setEstado(n.getEstado());
                    detalleMatriculaRepository.save(detalle);
                }
        );
        return matriculaSave;
    }

}
