package com.example.proyecto_satapp_Carlos_Rafa.util;

import java.time.LocalDateTime;

public record EditIncidenciaCmd(
        LocalDateTime fechaIncidencia,
        String titulo,
        String descripcion,
        boolean urgencia,
        Long equipoId
) {
}
