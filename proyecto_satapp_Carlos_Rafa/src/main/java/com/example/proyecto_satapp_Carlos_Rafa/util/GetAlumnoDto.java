package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;

import java.util.List;

public record GetAlumnoDto(
        Long id,
        String username,
        String password,
        String email,
        String role,
        List<GetHistoricoCursoDto> historicoCursos
) {
    public static GetAlumnoDto of(Alumno a){
        return  new GetAlumnoDto(
                a.getId(),
                a.getUsername(),
                a.getPassword(),
                a.getEmail(),
                a.getRole(),
                a.getHistoricoCursos().stream().map(GetHistoricoCursoDto::of).toList()
        );
    }
}
