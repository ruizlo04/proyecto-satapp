package com.example.proyecto_satapp_Carlos_Rafa.error;

public class EquipoNotFoundExcepcion extends RuntimeException {

    public EquipoNotFoundExcepcion(String message) {
        super(message);
    }

    public EquipoNotFoundExcepcion(Long id) {
        super("No existen equipos con ese id");
    }

    public EquipoNotFoundExcepcion() {
        super("No hay equipos para esa búsqueda");
    }
}
