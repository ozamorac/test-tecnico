package com.example.test.controller;

import com.example.test.entity.Matricula;
import com.example.test.request.MatriculaRequest;
import com.example.test.response.GetEvaluacionResponse;
import com.example.test.response.MatriculaResponse;
import com.example.test.service.EvaluacionService;
import com.example.test.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluacion")
public class EvaluacionController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping("/getEvaluacion/{idAlumno}/curso/{idCurso}/leccion/{idLeccion}")
    public ResponseEntity<?> getEvaluacionByAlumnoAndCurso(@PathVariable String idAlumno, @PathVariable String idCurso
            , @PathVariable String idLeccion) {

        GetEvaluacionResponse evaluacion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            evaluacion = evaluacionService.findEvaluacionByAlumnoAndCursoAndLeccion(Long.valueOf(idAlumno),
                    Long.valueOf(idCurso),Long.valueOf(idLeccion));

        } catch (Exception e) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (evaluacion == null) {
            response.put("Mensaje", "No existen registros en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GetEvaluacionResponse>(evaluacion, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMatricula(@Valid @RequestBody MatriculaRequest request, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            return buildResponseError(errors);
        }

        Map<String, Object> response = new HashMap<>();

        Matricula newMatricula = null;
        try {
            newMatricula = matriculaService.saveMatricula(request);

        } catch (Exception e) {
            response.put("Mensaje", "Error al registrar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "Registro creado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> buildResponseError(List<String> errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", errors);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
