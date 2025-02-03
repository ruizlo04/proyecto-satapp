package com.example.proyecto_satapp_Carlos_Rafa.controllers;

import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.services.UsuarioService;
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
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "username": "Rafahm03",
                                                "password": 101,
                                                "email": "tudeveloperfav@gmail.com",
                                                "role": "tecnico",
                                                "listaIncidencias": []
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

    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id){
        return GetUsuarioDto.of(usuarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
