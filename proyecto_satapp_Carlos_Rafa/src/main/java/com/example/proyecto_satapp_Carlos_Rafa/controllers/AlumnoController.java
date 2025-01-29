package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.services.AlumnoService;
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
@RequestMapping("/alumno")
@RequiredArgsConstructor
@Tag(name = "Alumno", description = "El controlador de alumno para gestionar todas las operaciones relacionadas con esta entidad")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Obtiene todas los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las alumnos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 2,
                                                "username": "Ruizlocar",
                                                "role": "alumno"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado alumnos"
            )
    })

    @GetMapping
    public ResponseEntity<List<Alumno>> getAll(){
        List<Alumno> result = alumnoService.findAll();
        if(result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un alumno por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el alumno",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el alumno con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable Long id){
        return ResponseEntity.of(alumnoService.findById(id));
    }

}
