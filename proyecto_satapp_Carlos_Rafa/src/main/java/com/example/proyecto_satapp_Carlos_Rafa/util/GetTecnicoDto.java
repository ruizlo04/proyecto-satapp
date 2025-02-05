package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import org.hibernate.mapping.Set;

import java.util.List;
import java.util.stream.Collectors;

public record GetTecnicoDto(
        Long id,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaDto> incidencias
) {
    public static GetTecnicoDto of(Tecnico t){
        return new GetTecnicoDto(
                t.getId(),
                t.getUsername(),
                t.getPassword(),
                t.getEmail(),
                t.getRole(),
                t.getIncidencias().stream().map(GetIncidenciaDto::of).toList()
        );
    }
}
