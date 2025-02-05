package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.HistoricoCursos;

import java.util.List;

public record EditAlumnoCmd(
        String username,
        String password,
        String email,
        String role,
        List<HistoricoCursos> historicoCursos
) {

}
