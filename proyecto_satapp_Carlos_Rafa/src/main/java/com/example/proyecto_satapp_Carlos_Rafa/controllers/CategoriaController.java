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
        return categoriaService.findAll().stream().map(GetCategoriaDto::of).toList();
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

    @Operation(summary = "Edita una categoria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Categoria editada con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditCategoriaCmd.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoria con el ID proporcionado",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para editar la categoria",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Categoria edit (@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la categoria", required = true,
            content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = EditEquipoCmd.class),
                            examples = @ExampleObject(value = """
                                {
                                                    "id": 2,
                                                    "nombre": "CategoriaRuizlo",
                                                    "incidencia": [],
                                                    "padre": null,
                                                    "subcategorias": []
                                }
                    """)))@RequestBody EditCategoriaCmd nuevaCategoriaCmd,
                           @PathVariable Long id) {
        return categoriaService.edit(id, nuevaCategoriaCmd);
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

    /*@Operation(summary = "Crea una nueva incidencia en categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Incidencia creada con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear la incidencia",
                    content = @Content)
    })
    @PostMapping("/{id}/incidencia/{idIncidencia}")
    public ResponseEntity<GetCategoriaDto> addIncidencia(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                   description = "Cuerpo de la incidencia", required = true,
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = GetCategoriaDto.class),
                           examples = @ExampleObject(value = """
                                {
                                    "nombre": "Equipo1",
                                    "caracteristicas": [
                                                "string"
                                              ],
                                    "ubicacionId": 1
                                }
                    """)))@PathVariable Long id, @PathVariable Long idIncidencia) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetCategoriaDto.of(categoriaService.addIncidencia(id, idIncidencia)));
    }*/

    @Operation(summary = "Elimina una incidencia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Incidencia eliminada con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}/incidencia/{idIncidencia}")
    public ResponseEntity<Categoria> deleteIncidencia(@PathVariable Long id,
                                                      @PathVariable Long idIncidencia) {
        categoriaService.removeIncidencia(id, idIncidencia);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crea una nueva subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Subcategoria creada con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear la subcategoria",
                    content = @Content)
    })
    @PostMapping("{id}/subcategoria")
    public ResponseEntity<GetCategoriaDto> addSubcategoria(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la subcategoria", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetCategoriaDto.class),
                    examples = @ExampleObject(value = """
                                {
                                    "nombre": "Equipo1",
                                    "caracteristicas": [
                                                "string"
                                              ],
                                    "ubicacionId": 1
                                }
                    """)))@PathVariable Long id, @RequestBody EditCategoriaCmd nuevaCategoriaCmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        GetCategoriaDto.of(categoriaService
                                .saveSubcategoria(id, nuevaCategoriaCmd)));
    }

    @Operation(summary = "Elimina una subcategoria por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Subcategoria eliminada con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la subcategoria con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("{id}/subcategoria/{idSubcategoria}")
    public ResponseEntity<GetCategoriaDto> deleteSubcategoria (@PathVariable Long id, @PathVariable Long idSubcategoria) {
        categoriaService.deleteSubcategoria(id, idSubcategoria);
        return ResponseEntity.noContent().build();
    }

    /*@Operation(summary = "Crea una nueva subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Subcategoria creada con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para crear la subcategoria",
                    content = @Content)
    })
    @PostMapping("{id}/subcategoria/{idSubcategoria}")
    public ResponseEntity<GetCategoriaDto> addSubcategoriaExistente(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la subcategoria", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetCategoriaDto.class),
                    examples = @ExampleObject(value = """
                                {
                                    "nombre": "Equipo1",
                                    "caracteristicas": [
                                                "string"
                                              ],
                                    "ubicacionId": 1
                                }
                    """)))@PathVariable Long id, @PathVariable Long idSubcategoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetCategoriaDto.of(categoriaService.addToSubcategoria(id, idSubcategoria)));
    }*/
}
