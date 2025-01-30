package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.services.UbicacionService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditUbicacionCmd;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                            array = @ArraySchema(schema = @Schema(implementation = EditUbicacionCmd.class)),
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
    public ResponseEntity<List<Ubicacion>> getAll() {
        List<Ubicacion> result = ubicacionService.findAll();

        if (result.isEmpty())

            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene una ubicacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la ubicacion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ubicacion.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicacion con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable Long id) {
        return ResponseEntity.of(ubicacionService.findById(id));
    }
}
