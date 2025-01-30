package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.services.PersonalService;
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
@RequestMapping("/personal")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "El controlador de personal para gestionar todas las operaciones relacionadas con esta entidad")
public class PersonalController {
    private final PersonalService personalService;


    @Operation(summary = "Obtiene todas los usuarios tipo personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los personal",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Personal.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 3,
                                                "username": "SoyYo",
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado personal"
            )
    })

    @GetMapping
    public ResponseEntity<List<Personal>> getAll(){
        List<Personal> result = personalService.findAll();
        if(result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un personal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el personal",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personal.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el personal con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable Long id){
        return ResponseEntity.of(personalService.findById(id));
    }
}
