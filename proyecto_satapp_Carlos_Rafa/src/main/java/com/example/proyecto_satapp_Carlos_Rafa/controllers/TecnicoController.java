package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;

import com.example.proyecto_satapp_Carlos_Rafa.services.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<Tecnico>> getAll(){
        List<Tecnico> result = tecnicoService.findAll();
        if(result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> getById(@PathVariable Long id){
        return ResponseEntity.of(tecnicoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
