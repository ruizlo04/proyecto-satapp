package com.example.proyecto_satapp_Carlos_Rafa.util;

import java.util.List;

public record EditEquipoCmd(
        String nombre,
        List <String> caracteristicas,
        Long ubicacionId
) {
}
