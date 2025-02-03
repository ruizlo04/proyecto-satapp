package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;

import java.util.List;

public record GetEquipoDto(
        String nombre,
        List <String> caracteristicas,
        Long ubicacionId
) {

    public static GetEquipoDto of (Equipo e){
        return new GetEquipoDto(
                e.getNombre(),
                e.getCaracteristicas(),
                e.getUbicacion().getId()
        );
    }
}
