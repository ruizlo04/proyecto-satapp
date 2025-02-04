package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.services.IncidenciaService;
import com.example.proyecto_satapp_Carlos_Rafa.services.TecnicoService;
import com.example.proyecto_satapp_Carlos_Rafa.util.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
@RequiredArgsConstructor
@Tag(name = "Tecnico", description = "El controlador de tecnico para gestionar todas las operaciones relacionadas con esta entidad")
public class TecnicoController {
    private final TecnicoService tecnicoService;
    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas los tecnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las tecnicos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
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

    @GetMapping("/")
    public List<GetTecnicoDto> getAll(){
        return tecnicoService.findAll().stream().map(GetTecnicoDto::of).toList();

    }

    @Operation(summary = "Obtiene un tecnico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el tecnico",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el tecnico con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public GetTecnicoDto getById(@PathVariable Long id){
        return GetTecnicoDto.of(tecnicoService.findById(id));
    }

    @PutMapping("/incidencias/{incidenciaId}")
    public ResponseEntity<Incidencia> gestionarIncidencia(
            @PathVariable Long incidenciaId,
            @RequestBody EditIncidenciaCmd incidenciaCmd) {

        Incidencia incidencia = tecnicoService.gestionarIncidencia(incidenciaId, incidenciaCmd);

        return ResponseEntity.ok(incidencia);
    }

    @PostMapping("/nuevo")
    public GetTecnicoDto saveTecnico(@RequestBody EditTecnicoCmd tecnicoNuevo) {
        Tecnico tecnico =  tecnicoService.saveTecnico(tecnicoNuevo);
        return GetTecnicoDto.of(tecnico);
    }

    @PutMapping("/{id}")
    public Tecnico edit(@RequestBody EditTecnicoCmd aEditar,
                         @PathVariable Long id) {
        return tecnicoService.edit(aEditar, id);
    }

}
