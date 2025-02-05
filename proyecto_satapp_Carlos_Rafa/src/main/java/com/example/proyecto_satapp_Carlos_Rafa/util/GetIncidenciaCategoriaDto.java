package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;

public record GetIncidenciaCategoriaDto(
        Long id,
        String titulo

) {

    public static GetIncidenciaCategoriaDto of(Incidencia incidencia) {
        return new GetIncidenciaCategoriaDto(
                incidencia.getId(),
                incidencia.getTitulo()

        );
    }
}
