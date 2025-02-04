package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.services.UsuarioService;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditUsuarioCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetIncidenciaDto;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetUsuarioDto;
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
                                                "username": "Rafahm03"
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
                                            "id": 10,
                                            "descripcion": "Error en la aplicación",
                                            "estado": "Abierta"
                                        }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el usuario con el ID proporcionado",
                    content = @Content)
    })

    @PostMapping("/{usuarioId}/incidencias")
    public ResponseEntity<GetIncidenciaDto> abrirIncidencia(@PathVariable Long usuarioId, @RequestBody EditIncidenciaCmd incidenciaCmd) {
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
    public Usuario edit(@RequestBody EditUsuarioCmd aEditar,
                         @PathVariable Long id) {
        return usuarioService.edit(aEditar, id);
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
                                            "username": "NuevoUsuario",
                                            "email": "usuario@example.com"
                                        }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Error en la solicitud de creación",
                    content = @Content)
    })

    @PostMapping("/nuevo")
    public ResponseEntity<Usuario> create(@RequestBody EditUsuarioCmd nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        usuarioService.save(nuevo));
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
