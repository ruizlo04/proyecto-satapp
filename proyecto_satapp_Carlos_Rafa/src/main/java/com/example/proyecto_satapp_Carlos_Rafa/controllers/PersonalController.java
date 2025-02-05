package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.services.PersonalService;
import com.example.proyecto_satapp_Carlos_Rafa.util.*;
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
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
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

    @GetMapping("/")
    public List<GetPersonalDto> getAll(){
        return personalService.findAll().stream().map(GetPersonalDto::of).toList();

    }

    @Operation(summary = "Obtiene un personal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el personal",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el personal con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public GetPersonalDto getById(@PathVariable Long id){
        return GetPersonalDto.of(personalService.findById(id));
    }

    @Operation(summary = "Crea un nuevo personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Personal creado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Error en los datos proporcionados",
                    content = @Content)
    })
    @PostMapping("/nuevo")
    public GetPersonalDto savePersonal(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del tecnico", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation= GetPersonalDto.class),
                    examples = @ExampleObject(value = """
                            {
                                "id": 101,
                                "username": "User1234",
                                "password": null,
                                "email": null,
                                "role": null,
                                "tipo": "PROFESOR"
                            }
                            
                            """)))@RequestBody EditPersonalCmd personalNuevo) {
        Personal personal =  personalService.savePersonal(personalNuevo);
        return GetPersonalDto.of(personal);
    }

    @Operation(summary = "Edita un personal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Personal editado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personal.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el personal con el ID proporcionado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Personal edit(@RequestBody EditPersonalCmd aEditar,
                       @PathVariable Long id) {
        return personalService.edit(aEditar, id);
    }


    @Operation(summary = "Elimina un personal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Personal eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el personal con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
