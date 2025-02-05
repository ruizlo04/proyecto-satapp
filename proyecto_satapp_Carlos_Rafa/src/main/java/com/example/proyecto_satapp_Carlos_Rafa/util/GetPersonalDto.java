package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tipo;

public record GetPersonalDto(
        Long id,
        String username,
        String password,
        String email,
        String role,
        Tipo tipo
) {

    public static GetPersonalDto of(Personal p){
        return new GetPersonalDto(
                p.getId(),
                p.getUsername(),
                p.getPassword(),
                p.getEmail(),
                p.getRole(),
                p.getTipo()
        );
    }
}
