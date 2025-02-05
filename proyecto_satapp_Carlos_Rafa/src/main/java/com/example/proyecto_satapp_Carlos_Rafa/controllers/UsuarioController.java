package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.services.IncidenciaService;
import com.example.proyecto_satapp_Carlos_Rafa.services.UsuarioService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "El controlador de usuario para gestionar todas las operaciones relacionadas con esta entidad")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas los usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                    "id": 1,
                                                    "username": "RuizloCar",
                                                    "password": null,
                                                    "email": null,
                                                    "role": null
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado usuarios"
            )
    })

    @GetMapping("/")
    public List<GetUsuarioDto> getAll(){
        return usuarioService.findAll().stream().map(GetUsuarioDto::of).toList();

    }


    @Operation(summary = "Obtiene un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el usuario",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUsuarioDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario con el ID proporcionado",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id){
        return GetUsuarioDto.of(usuarioService.findById(id));
    }

    @Operation(summary = "Abre una nueva incidencia para un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Incidencia creada exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                    "id": 1,
                                                    "username": "RuizloCar",
                                                    "password": null,
                                                    "email": null,
                                                    "role": null
                                                }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el usuario con el ID proporcionado",
                    content = @Content)
    })


    @PostMapping("/{id}/notas")
    public ResponseEntity<GetIncidenciaDto> addNotaToIncidencia(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la incidencia", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditIncidenciaCmd.class),
                    examples = @ExampleObject(value = """
                                {
                                          "fecha": "2025-02-05",
                                          "contenido": "string",
                                          "autor": "string",
                                          "incidenciaId": 0
                                }
                    """)))@PathVariable Long id, @RequestBody EditNotaCmd nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidenciaService.addNotaToIncidencia(id, nota)));
    }

    @PostMapping("/{usuarioId}/incidencias")
    public ResponseEntity<GetIncidenciaDto> abrirIncidencia(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Abrir una incidencia como usuario", required = true,
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = EditIncidenciaCmd.class),
            examples = @ExampleObject(value = """
                    {
                        "fechaIncidencia": null,
                        "titulo": "incidencia2",
                        "descripcion": "esta incidencia es de prueba",
                        "urgencia": true,
                        "estado": "ABIERTA",
                        "notas": [],
                        "usuario": {
                            "id": 1,
                            "username": "RuizloCar",
                            "password": null,
                            "email": null,
                            "role": null
                        },
                        "tecnicos": []
                    }
                    """)))@PathVariable Long usuarioId, @RequestBody EditIncidenciaCmd incidenciaCmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(usuarioService.abrirIncidencia(usuarioId, incidenciaCmd)));
    }

    @Operation(summary = "Edita un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario editado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el usuario con el ID proporcionado",
                    content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> edit(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del usuario a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditUsuarioCmd.class),
                    examples = @ExampleObject(value = """
                                {
                                                    "id": 1,
                                                    "username": "RuizloCar",
                                                    "password": null,
                                                    "email": null,
                                                    "role": null
                                                }
                    """)))
                                        @RequestBody EditUsuarioCmd aEditar, @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.edit(aEditar, id));
    }

    @Operation(summary = "Crea un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuario creado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                    "id": 1,
                                                    "username": "RuizloCar",
                                                    "password": null,
                                                    "email": null,
                                                    "role": null
                                                }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Error en la solicitud de creación",
                    content = @Content)
    })

    @PostMapping("/nuevo")
    public ResponseEntity<Usuario> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del usuario", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditUsuarioCmd.class),
                    examples = @ExampleObject(value = """
                                {
                                    "username": "NuevoUsuario",
                                    "email": "usuario@example.com",
                                    "password": "contraseñaSegura"
                                }
                    """)))
                                          @RequestBody EditUsuarioCmd nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.save(nuevo));
    }


    @Operation(summary = "Elimina un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Usuario eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el usuario con el ID proporcionado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
