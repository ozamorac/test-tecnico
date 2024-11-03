package com.example.test.service.impl;

import com.example.test.entity.Leccion;
import com.example.test.entity.Options;
import com.example.test.entity.Pregunta;
import com.example.test.repository.LeccionRepository;
import com.example.test.repository.OpcionRepository;
import com.example.test.repository.PreguntaRepository;
import com.example.test.request.LeccionRequest;
import com.example.test.request.PreguntaRequest;
import com.example.test.response.OptionResponse;
import com.example.test.response.PreguntaResponse;
import com.example.test.service.LeccionService;
import com.example.test.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private OpcionRepository opcionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PreguntaResponse> findAllPreguntas() {
        List<PreguntaResponse> listaPreguntasResponse = new ArrayList<PreguntaResponse>();


        List<Pregunta> listaPreguntas = preguntaRepository.findAllPreguntasActivos();
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
        return listaPreguntasResponse;
    }

    @Override
    public PreguntaResponse findPreguntaById(Integer id) {
        PreguntaResponse preguntaResponse = new PreguntaResponse();
        List<OptionResponse> listaOpcionesResponse = new ArrayList<OptionResponse>();

        List<Options> listaOpciones = opcionRepository.findAllOpcionesByPregunta(id);
        listaOpciones.stream().forEach(l -> {
                    listaOpcionesResponse.add(
                            new OptionResponse(l.getIdOption(), l.getDescripcion(), l.getTipo(), l.getScore(), l.getIsCorrectAnswer(),
                                    l.getEstado())
                    );
                }
        );

        Optional<Pregunta> pregunta = Optional.ofNullable(preguntaRepository.findById(id).orElse(null));
        preguntaResponse.setIdPregunta(pregunta.get().getIdPregunta());
        preguntaResponse.setIdLeccion(pregunta.get().getIdLeccion());
        preguntaResponse.setDescripcion(pregunta.get().getDescripcion());
        preguntaResponse.setTipo(pregunta.get().getTipo());
        preguntaResponse.setPuntaje(pregunta.get().getPuntaje());
        preguntaResponse.setEstado(pregunta.get().getEstado());
        preguntaResponse.setOpciones(listaOpcionesResponse);
        return preguntaResponse;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Pregunta savePregunta(PreguntaRequest preguntaRequest) {

        Pregunta pregunta = new Pregunta();

        pregunta.setDescripcion(preguntaRequest.getDescripcion());
        pregunta.setIdLeccion(preguntaRequest.getIdLeccion());
        pregunta.setTipo(preguntaRequest.getTipo());
        pregunta.setPuntaje(preguntaRequest.getPuntaje());
        pregunta.setEstado(preguntaRequest.getEstado());
        Pregunta preguntaSave = preguntaRepository.save(pregunta);
        preguntaRequest.getOpciones().stream().forEach(n -> {
                    Options option = new Options();
                    option.setIdPregunta(preguntaSave.getIdPregunta());
                    option.setDescripcion(n.getDescripcion());
                    option.setTipo(n.getTipo());
                    option.setScore(n.getScore());
                    option.setIsCorrectAnswer(n.getIsCorrectAnswer());
                    option.setEstado(n.getEstado());
                    opcionRepository.save(option);
                }
        );
        return preguntaSave;


    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Pregunta editPregunta(PreguntaRequest preguntaRequest) {
        Pregunta pregunta = new Pregunta();

        pregunta.setIdPregunta(preguntaRequest.getIdPregunta());
        pregunta.setIdLeccion(preguntaRequest.getIdLeccion());
        pregunta.setDescripcion(preguntaRequest.getDescripcion());
        pregunta.setTipo(preguntaRequest.getTipo());
        pregunta.setPuntaje(preguntaRequest.getPuntaje());
        pregunta.setEstado(preguntaRequest.getEstado());
        Pregunta preguntaUpdate = preguntaRepository.save(pregunta);
        preguntaRequest.getOpciones().stream().forEach(n -> {
            Options option = new Options();
            option.setIdOption(n.getIdOption());
            option.setIdPregunta(preguntaUpdate.getIdPregunta());
            option.setDescripcion(n.getDescripcion());
            option.setTipo(n.getTipo());
            option.setScore(n.getScore());
            option.setIsCorrectAnswer(n.getIsCorrectAnswer());
            option.setEstado(n.getEstado());
            opcionRepository.save(option);
        });
        return preguntaUpdate;
    }

    @Override
    public Pregunta removePregunta(Integer id) {
        Optional<Pregunta> pregunta = Optional.ofNullable(preguntaRepository.findById(id).orElse(null));
        if (pregunta!=null) {
            Pregunta preguntaDelete = new Pregunta();

            preguntaDelete.setIdLeccion(pregunta.get().getIdLeccion());
            preguntaDelete.setDescripcion(pregunta.get().getDescripcion());
            preguntaDelete.setTipo(pregunta.get().getTipo());
            preguntaDelete.setPuntaje(pregunta.get().getPuntaje());
            preguntaDelete.setEstado("INACTIVO");
            return preguntaRepository.save(preguntaDelete);
        }
        return null;
    }

}
