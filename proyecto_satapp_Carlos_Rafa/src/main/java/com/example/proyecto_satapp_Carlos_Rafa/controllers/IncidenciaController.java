package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.services.IncidenciaService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidencia")
@RequiredArgsConstructor
@Tag(name = "Bicicleta", description = "El controlador de bicicletas para gestionar todas las operaciones relacionadas con ellas")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado incidencias",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EditIncidenciaCmd.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"titulo": "incidencia1"}
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado incidencias",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<Incidencia>> getAll() {
        List<Incidencia> result = incidenciaService.findAll();

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }


    @Operation(summary = "Obtiene una incidencia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la incidencia",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable Long id) {
        return ResponseEntity.of(incidenciaService.findById(id));
    }

}
