package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;

public record GetSubcategoriaDto(
        Long id,
        String nombre
) {

    public static GetSubcategoriaDto of(Categoria categoria) {
        return new GetSubcategoriaDto(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}