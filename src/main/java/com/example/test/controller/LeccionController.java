package com.example.test.controller;

import com.example.test.entity.Leccion;
import com.example.test.request.LeccionRequest;
import com.example.test.service.LeccionService;
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
@RequestMapping("/leccion")
public class LeccionController {

    @Autowired
    private LeccionService leccionService;

    @GetMapping(path = "/listar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarLecciones() {

        List<Leccion> listLeccion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            listLeccion = leccionService.findAllLecciones();
        } catch (Exception e) {
            response.put("Mensaje", e.getMessage());
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (listLeccion == null || listLeccion.size()==0) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Leccion>>(listLeccion, HttpStatus.OK);
    }

    @GetMapping("/getLeccion/{id}")
    public ResponseEntity<?> getLeccion(@PathVariable String id) {

        Optional<Leccion> leccion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            leccion = leccionService.findLeccionById(Long.valueOf(id));
        } catch (Exception e) {
            response.put("Mensaje", "No se encontraron datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (!leccion.isPresent()) {
            response.put("Mensaje", "La leccion no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Leccion>>(leccion, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLeccion(@Valid @RequestBody LeccionRequest request, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            return buildResponseError(errors);
        }

        Map<String, Object> response = new HashMap<>();

        Leccion newLeccion = null;
        try {
            newLeccion = leccionService.saveLeccion(request);

        } catch (Exception e) {
            response.put("Mensaje", "Error al registrar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "Registro creado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editLeccion(@RequestBody LeccionRequest leccionRequest, BindingResult result, @PathVariable String id) {

        leccionRequest.setIdLeccion(Long.valueOf(id));
        Leccion updatedLeccion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            updatedLeccion = leccionService.editLeccion(leccionRequest);

        } catch (Exception e) {
            response.put("Mensaje", "Error al actualizar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("Mensaje", "Registro actualizado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLeccion(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Leccion leccion = null;
        try {

            leccion = leccionService.removeLeccion(Long.valueOf(id));

        } catch (Exception e) {
            response.put("Mensaje", "Error al eliminar los datos: " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Mensaje", "La leccion ha sido desactivado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> buildResponseError(List<String> errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", errors);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
