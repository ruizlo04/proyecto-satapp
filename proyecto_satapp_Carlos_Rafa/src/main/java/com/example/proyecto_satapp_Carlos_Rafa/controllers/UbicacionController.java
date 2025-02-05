package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.services.UbicacionService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditUbicacionCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetUbicacionDto;
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
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
@Tag(name = "Ubicacion", description = "El controlador de ubicaciones para gestionar todas las operaciones relacionadas con ellas")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Obtiene todas las ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado ubicaciones",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"nombre": "Ubicacion1"}
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado ubicaciones",
                    content = @Content)
    })
    @GetMapping("/")
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAll().stream().map(GetUbicacionDto::of).toList();
    }

    @Operation(summary = "Obtiene una ubicacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la ubicacion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicacion con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetUbicacionDto getById(@PathVariable Long id) {
        return GetUbicacionDto.of(ubicacionService.findById(id));
    }

    @Operation(summary = "Obtiene una ubicacion por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la ubicacion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicacion con el nombre proporcionado",
                    content = @Content)
    })
    @GetMapping("/nombre/{nombre}")
    public GetUbicacionDto getByNombre(@PathVariable String nombre) {
        return GetUbicacionDto.of(ubicacionService.findByNombre(nombre));
    }

    @Operation(summary = "Crea una nueva ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Ubicación creada con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear la ubicación",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<GetUbicacionDto> create(@RequestBody GetUbicacionDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.createUbicacion(nuevo)));
    }

    @Operation(summary = "Elimina una ubicación por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Ubicación eliminada con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicación con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ubicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
