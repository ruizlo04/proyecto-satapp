package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.services.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping("/")
    public ResponseEntity<List<Ubicacion>> getAll() {
        List<Ubicacion> result = ubicacionService.findAll();

        if (result.isEmpty())

            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable Long id) {
        return ResponseEntity.of(ubicacionService.findById(id));
    }
}
