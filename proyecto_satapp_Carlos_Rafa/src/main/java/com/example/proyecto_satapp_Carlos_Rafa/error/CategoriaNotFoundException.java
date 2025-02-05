package com.example.proyecto_satapp_Carlos_Rafa.error;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String message) {
        super(message);
    }

    public CategoriaNotFoundException(Long id) {
        super("No existen categorias con ese id");
    }

    public CategoriaNotFoundException() {
        super("No hay categorias para esa búsqueda");
    }
}
