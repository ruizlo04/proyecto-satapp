package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.services.CategoriaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "El controlador de categorias para gestionar todas las operaciones relacionadas con ellos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Obtiene todas las categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado categorias",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                           [
                                               {
                                                        "id": 1,
                                                        "nombre": "Categoria1",
                                                        "incidencia": []
                                               }
                                           ]
                                           """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado categorias",
                    content = @Content)
    })
    @GetMapping("/")
    private List<GetCategoriaDto> getAll(){
        return categoriaService.findAll();
    }

    @Operation(summary = "Obtiene una categoria por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoria con el ID proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {

        return GetCategoriaDto.of(categoriaService.findById(id));

    }

    @PostMapping("/")
    public ResponseEntity<GetCategoriaDto> create(@RequestBody EditCategoriaCmd nuevaCategoriaCmd) {
        Categoria categoria = categoriaService.save(nuevaCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        GetCategoriaDto.of(categoria));
    }

    @Operation(summary = "Elimina una categoria por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Categoria eliminada con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoria con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
