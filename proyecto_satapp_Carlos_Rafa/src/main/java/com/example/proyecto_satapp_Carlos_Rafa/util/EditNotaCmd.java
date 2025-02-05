package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;

import java.time.LocalDate;

public record EditNotaCmd(
        LocalDate fecha,
        String contenido,
        String autor,
        Long incidenciaId
) {
}
