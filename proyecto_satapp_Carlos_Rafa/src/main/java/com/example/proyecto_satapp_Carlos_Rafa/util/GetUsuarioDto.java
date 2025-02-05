package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public record GetUsuarioDto(
        Long id,
        String username,
        String password,
        String email,
        String role
) {

    public static GetUsuarioDto of(Usuario u){
        return new GetUsuarioDto(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getRole()
        );
    }
}
