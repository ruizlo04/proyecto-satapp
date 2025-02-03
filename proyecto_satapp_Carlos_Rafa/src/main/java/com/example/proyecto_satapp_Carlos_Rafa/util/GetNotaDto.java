package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Nota;

import java.time.LocalDate;

public record GetNotaDto(
        LocalDate fecha,
        String contenido,
        String autor,
        Long incidenciaId
) {

    public static GetNotaDto of(Nota n){
        return new GetNotaDto(
                n.getFecha(),
                n.getContenido(),
                n.getAutor(),
                n.getIncidencia().getId()
        );
    }
}
