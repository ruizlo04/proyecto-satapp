package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Tipo;

public record EditPersonalCmd(
        String username,
        String password,
        String email,
        String role,
        Tipo tipo
) {
}
