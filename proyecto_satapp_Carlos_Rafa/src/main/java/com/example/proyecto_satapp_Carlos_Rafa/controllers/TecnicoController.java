package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.services.TecnicoService;
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
@RequestMapping("/tecnico")
@RequiredArgsConstructor
@Tag(name = "Tecnico", description = "El controlador de tecnico para gestionar todas las operaciones relacionadas con esta entidad")
public class TecnicoController {
    private final TecnicoService tecnicoService;

    @Operation(summary = "Obtiene todas los tecnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las tecnicos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Tecnico.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "username": "SoyYo",
                                                "role": "tecnico"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado tecnicos"
            )
    })

    @GetMapping
    public ResponseEntity<List<Tecnico>> getAll(){
        List<Tecnico> result = tecnicoService.findAll();
        if(result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un tecnico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el tecnico",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tecnico.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el tecnico con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> getById(@PathVariable Long id){
        return ResponseEntity.of(tecnicoService.findById(id));
    }

}
