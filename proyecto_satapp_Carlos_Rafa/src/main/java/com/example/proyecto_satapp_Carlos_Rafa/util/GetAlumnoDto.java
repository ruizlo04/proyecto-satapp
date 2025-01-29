package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;

public record GetAlumnoDto(
        Long id,
        String username,
        String password,
        String email,
        String role
) {
    public static GetAlumnoDto of(Alumno a){
        return  new GetAlumnoDto(
                a.getId(),
                a.getUsername(),
                a.getPassword(),
                a.getEmail(),
                a.getRole()
        );
    }
}
