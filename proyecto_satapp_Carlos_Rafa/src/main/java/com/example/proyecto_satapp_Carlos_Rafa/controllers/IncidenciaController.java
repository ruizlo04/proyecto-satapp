package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Nota;
import com.example.proyecto_satapp_Carlos_Rafa.services.IncidenciaService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditNotaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetIncidenciaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidencia")
@RequiredArgsConstructor
@Tag(name = "Incidencia", description = "El controlador de incidencias para gestionar todas las operaciones relacionadas con ellas")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado incidencias",
                    content = {@Content(mediaType = "application/json",
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
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable Long id) {
        return ResponseEntity.of(incidenciaService.findById(id));
    }


    @Operation(summary = "Crea una nueva incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Incidencia creada con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear la incidencia",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Incidencia> create(@RequestBody EditIncidenciaCmd nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incidenciaService.save(nuevo));
    }

    @Operation(summary = "Elimina una incidencia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Incidencia eliminada con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Añade una nota a una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Nota añadida a la incidencia con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @PostMapping("/{id}/notas")
    public ResponseEntity<GetIncidenciaDto> addNotaToIncidencia(@PathVariable Long id, @RequestBody EditNotaCmd nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidenciaService.addNotaToIncidencia(id, nota)));
    }

    @Operation(summary = "Elimina una nota de una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Nota eliminada de la incidencia con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}/notas")
    public ResponseEntity<Incidencia> removeNotaFromIncidencia(@PathVariable Long id, @RequestBody Nota nota) {
        Optional<Incidencia> incidenciaOptional = incidenciaService.findById(id);

        if (incidenciaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Incidencia updatedIncidencia = incidenciaService.removeNotaFromIncidencia(id, nota);
        return ResponseEntity.ok(updatedIncidencia);
    }

}
