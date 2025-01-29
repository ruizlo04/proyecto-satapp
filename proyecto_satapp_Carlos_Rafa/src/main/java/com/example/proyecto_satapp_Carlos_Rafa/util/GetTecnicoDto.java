package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;

public record GetTecnicoDto(
        Long id,
        String username,
        String password,
        String email,
        String role
) {
    public static GetTecnicoDto of(Tecnico t){
        return new GetTecnicoDto(
                t.getId(),
                t.getUsername(),
                t.getPassword(),
                t.getEmail(),
                t.getRole()
        );
    }
}
