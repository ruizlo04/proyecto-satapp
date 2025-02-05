package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.TipoEstado;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;

import java.time.LocalDateTime;

public record EditIncidenciaCmd(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        boolean urgencia,
        TipoEstado estado,
        Long ubicacionId,
        Long equipoId,
        Long usuarioId
) {
}
