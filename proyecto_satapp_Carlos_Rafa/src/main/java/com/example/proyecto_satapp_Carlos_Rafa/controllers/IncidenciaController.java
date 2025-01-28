package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.services.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidencia")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @GetMapping("/")
    public ResponseEntity<List<Incidencia>> getAll() {
        List<Incidencia> result = incidenciaService.findAll();

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable Long id) {
        return ResponseEntity.of(incidenciaService.findById(id));
    }


}
