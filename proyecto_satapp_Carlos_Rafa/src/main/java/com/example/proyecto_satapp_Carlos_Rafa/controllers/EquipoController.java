package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.services.EquipoService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditEquipoCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetEquipoDto;
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

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
@Tag(name = "Equipo", description = "El controlador de equipos para gestionar todas las operaciones relacionadas con ellos")
public class EquipoController {

    private final EquipoService equipoService;

    @Operation(summary = "Obtiene todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado equipos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                           [
                                               {"nombre": "equipo1"}
                                           ]
                                           """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado equipos",
                    content = @Content)
    })
    @GetMapping("/")
    public List <GetEquipoDto> getAll() {
        return equipoService.findAll().stream().map(GetEquipoDto::of).toList();

    }

    @Operation(summary = "Obtiene un equipo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el equipo",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el equipo con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetEquipoDto getById(@PathVariable Long id) {
        return GetEquipoDto.of(equipoService.findById(id));
    }

    @Operation(summary = "Crea un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Equipo creado con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear el equipo",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<GetEquipoDto> create(@RequestBody EditEquipoCmd nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoDto.of(equipoService.save(nuevo)));
    }
}
