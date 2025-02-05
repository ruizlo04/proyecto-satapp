package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;

public record GetCategoriaPadreDto(
        String nombre
) {

    public static GetCategoriaPadreDto of(Categoria categoria) {
        return new GetCategoriaPadreDto(
                categoria.getNombre()
        );
    }
}
