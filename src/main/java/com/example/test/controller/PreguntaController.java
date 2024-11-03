package com.example.test.controller;

import com.example.test.entity.Pregunta;
import com.example.test.request.PreguntaRequest;
import com.example.test.response.PreguntaResponse;
import com.example.test.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @GetMapping(path = "/listar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarPreguntas() {

        List<PreguntaResponse> listPregunta = null;
        Map<String, Object> response = new HashMap<>();
        try {
            listPregunta = preguntaService.findAllPreguntas();
        } catch (Exception e) {
            response.put("Mensaje", e.getMessage());
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (listPregunta == null || listPregunta.size()==0) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<PreguntaResponse>>(listPregunta, HttpStatus.OK);
    }

    @GetMapping("/getPregunta/{id}")
    public ResponseEntity<?> getPregunta(@PathVariable String id) {

        PreguntaResponse pregunta = null;
        Map<String, Object> response = new HashMap<>();
        try {
            pregunta = preguntaService.findPreguntaById(Integer.valueOf(id));
        } catch (Exception e) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (pregunta == null) {
            response.put("Mensaje", "La Pregunta no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PreguntaResponse>(pregunta, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPregunta(@Valid @RequestBody PreguntaRequest request, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            return buildResponseError(errors);
        }

        Map<String, Object> response = new HashMap<>();

        Pregunta newPregunta = null;
        try {
            newPregunta = preguntaService.savePregunta(request);

        } catch (Exception e) {
            response.put("Mensaje", "Error al registrar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "Registro creado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editPregunta(@RequestBody PreguntaRequest preguntaRequest, BindingResult result, @PathVariable String id) {

        preguntaRequest.setIdPregunta(Integer.valueOf(id));
        Pregunta updatedPregunta = null;
        Map<String, Object> response = new HashMap<>();
        try {
            updatedPregunta = preguntaService.editPregunta(preguntaRequest);

        } catch (Exception e) {
            response.put("Mensaje", "Error al actualizar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("Mensaje", "Registro actualizado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePregunta(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Pregunta leccion = null;
        try {

            leccion = preguntaService.removePregunta(Integer.valueOf(id));

        } catch (Exception e) {
            response.put("Mensaje", "Error al eliminar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "La Pregunta ha sido desactivado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> buildResponseError(List<String> errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", errors);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
