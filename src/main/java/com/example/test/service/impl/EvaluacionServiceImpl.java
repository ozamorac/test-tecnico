package com.example.test.service.impl;

import com.example.test.entity.*;
import com.example.test.repository.*;
import com.example.test.response.*;
import com.example.test.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private LeccionRepository leccionRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DetalleMatriculaRepository detalleMatriculaRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private OpcionRepository opcionRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public GetEvaluacionResponse findEvaluacionByAlumnoAndCursoAndLeccion(Long idAlumno, Long idCurso, Long idLeccion) {

        Matricula matricula = matriculaRepository.findMatriculaByAlumno(idAlumno);
        List<PreguntaResponse> listaPreguntasResponse = new ArrayList<PreguntaResponse>();

        if (matricula != null) {
            DetalleMatricula detalle = detalleMatriculaRepository.findByIdAndIdCurso(Integer.valueOf(matricula.getIdMatricula().toString()),
                    Integer.valueOf(idCurso.toString()));
            
            if (detalle != null) {
                Optional<Alumno> alumno = Optional.ofNullable(alumnoRepository.findById(idAlumno).orElse(null));
                Optional<Curso> curso = Optional.ofNullable(cursoRepository.findById(idCurso).orElse(null));
                Optional<Leccion> leccion = Optional.ofNullable(leccionRepository.findById(idLeccion).orElse(null));
                Optional<Carrera> carrera = Optional.ofNullable(carreraRepository.findById(matricula.getIdCarrera()).orElse(null));

                List<Pregunta> listaPreguntas = preguntaRepository.findAllPreguntasbyLeccion(Integer.valueOf(idLeccion.toString()));

                listaPreguntas.stream().forEach(n -> {
                    List<OptionResponse> listaOpcionesResponse = new ArrayList<OptionResponse>();
                    List<Options> listaOpciones = opcionRepository.findAllOpcionesByPregunta(n.getIdPregunta());
                    listaOpciones.stream().forEach(l -> {
                                listaOpcionesResponse.add(
                                        new OptionResponse(l.getIdOption(), l.getDescripcion(), l.getTipo(), l.getScore(), l.getIsCorrectAnswer(),
                                                l.getEstado())
                                );
                            }
                    );
                    listaPreguntasResponse.add(
                            new PreguntaResponse(
                                    n.getIdPregunta(), n.getIdLeccion(), n.getDescripcion(), n.getTipo(), n.getPuntaje(),
                                    n.getEstado(), listaOpcionesResponse
                            )
                    );
                });

                GetEvaluacionResponse getEvaluacion = getGetEvaluacionResponse(alumno, curso, leccion, carrera, listaPreguntasResponse);

                return getEvaluacion;
            }
        }

        return null;
    }

    private static GetEvaluacionResponse getGetEvaluacionResponse(Optional<Alumno> alumno, Optional<Curso> curso, Optional<Leccion> leccion, Optional<Carrera> carrera,
                                                                  List<PreguntaResponse> listaPreguntasResponse) {
        GetEvaluacionResponse getEvaluacion = new GetEvaluacionResponse();
        getEvaluacion.setAlumno(new AlumnoResponse(alumno.get().getIdAlumno(), alumno.get().getNombre(), alumno.get().getApellido()));
        getEvaluacion.setCurso(new CursoResponse(curso.get().getIdCurso(), curso.get().getNombre(), curso.get().getCiclo(),
                new CarreraResponse(carrera.get().getIdCarrera(), carrera.get().getNomCarrera()), curso.get().getAnio()));
        getEvaluacion.setLeccion(new LeccionResponse(leccion.get().getIdLeccion(), leccion.get().getNombre()));
        getEvaluacion.setEstado("PENDIENTE DE EVALUACION");

        getEvaluacion.setPreguntas(listaPreguntasResponse);
        return getEvaluacion;
    }
}
