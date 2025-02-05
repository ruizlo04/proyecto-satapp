package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;

import java.util.List;

public record GetCategoriaDto(
        Long id,
        String nombre,
        List<GetIncidenciaDto> incidencia,
        GetCategoriaPadreDto padre,
        List<GetSubcategoriaDto> subcategorias
) {

    public static GetCategoriaDto of(Categoria categoria){
        return new GetCategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getIncidencias().stream()
                        .map(GetIncidenciaDto::of)
                        .toList(),
                categoria.getPadre() != null ? GetCategoriaPadreDto.of(categoria.getPadre()) : null,
                categoria.getSubcategoria().stream()
                        .map(GetSubcategoriaDto::of)
                        .toList()
        );
    }
}
