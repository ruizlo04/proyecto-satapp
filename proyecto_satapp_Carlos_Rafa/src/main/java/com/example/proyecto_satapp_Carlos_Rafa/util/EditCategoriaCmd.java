package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;

import java.util.List;

public record EditCategoriaCmd(
        String nombre,
        List<Categoria> subCategorias,
        Categoria categoriaPadre
) {
}
