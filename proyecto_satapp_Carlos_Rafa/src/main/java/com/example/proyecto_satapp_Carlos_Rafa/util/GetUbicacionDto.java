package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;

public record GetUbicacionDto(
        String nombre
) {

    public static GetUbicacionDto of (Ubicacion u){
        return new GetUbicacionDto(
                u.getNombre()
        );
    }
}
