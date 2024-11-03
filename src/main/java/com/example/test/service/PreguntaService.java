package com.example.test.service;

import com.example.test.entity.Pregunta;
import com.example.test.request.PreguntaRequest;
import com.example.test.response.PreguntaResponse;

import java.util.List;
import java.util.Optional;

public interface PreguntaService {

    List<PreguntaResponse> findAllPreguntas();

    PreguntaResponse findPreguntaById(Integer id);

    Pregunta savePregunta(PreguntaRequest preguntaRequest);

    Pregunta editPregunta(PreguntaRequest preguntaRequest);

    Pregunta removePregunta(Integer id);

}
