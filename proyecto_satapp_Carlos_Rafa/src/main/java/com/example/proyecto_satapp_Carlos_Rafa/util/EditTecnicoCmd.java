package com.example.proyecto_satapp_Carlos_Rafa.util;

public record EditTecnicoCmd(
        String username,
        String password,
        String email,
        String role
) {
}
