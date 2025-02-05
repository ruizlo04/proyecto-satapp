package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;

public record EditUbicacionCmd(
        String nombre
) {

    public static EditUbicacionCmd of (Ubicacion ubicacion){
        return new EditUbicacionCmd(ubicacion.getNombre());
    }
}
