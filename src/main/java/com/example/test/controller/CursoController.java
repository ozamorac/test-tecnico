package com.example.test.controller;

import com.example.test.entity.Curso;
import com.example.test.request.CursoRequest;
import com.example.test.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(path = "/listar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarCursos() {

        List<Curso> listCurso = null;
        Map<String, Object> response = new HashMap<>();
        try {
            listCurso = cursoService.findAllCursos();
        } catch (Exception e) {
            response.put("Mensaje", e.getMessage());
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (listCurso == null || listCurso.size()==0) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Curso>>(listCurso, HttpStatus.OK);
    }

    @GetMapping("/getCurso/{id}")
    public ResponseEntity<?> getCurso(@PathVariable String id) {

        Optional<Curso> curso = null;
        Map<String, Object> response = new HashMap<>();
        try {
            curso = cursoService.findCursoById(Long.valueOf(id));
        } catch (Exception e) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (!curso.isPresent()) {
            response.put("Mensaje", "El curso no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Curso>>(curso, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCurso(@Valid @RequestBody CursoRequest request, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            return buildResponseError(errors);
        }

        Map<String, Object> response = new HashMap<>();

        Curso newCurso = null;
        try {
            newCurso = cursoService.saveCurso(request);

        } catch (Exception e) {
            response.put("Mensaje", "Error al registrar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "Registro creado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editCurso(@RequestBody CursoRequest cursoRequest, BindingResult result, @PathVariable String id) {

        cursoRequest.setIdCurso(Long.valueOf(id));
        Curso updatedCurso = null;
        Map<String, Object> response = new HashMap<>();
        try {
            updatedCurso = cursoService.editCurso(cursoRequest);

        } catch (Exception e) {
            response.put("Mensaje", "Error al actualizar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("Mensaje", "Registro actualizado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Curso curso = null;
        try {

            curso = cursoService.removeCurso(Long.valueOf(id));

        } catch (Exception e) {
            response.put("Mensaje", "Error al eliminar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "El Curso ha sido desactivado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> buildResponseError(List<String> errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", errors);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
