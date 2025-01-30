package com.example.proyecto_satapp_Carlos_Rafa.util;

public record EditUsuarioCmd(
        String username,
        String password,
        String role,
        String email
) {
}
