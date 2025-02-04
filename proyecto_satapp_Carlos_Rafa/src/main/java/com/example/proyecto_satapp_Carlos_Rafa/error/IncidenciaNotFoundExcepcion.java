package com.example.proyecto_satapp_Carlos_Rafa.error;

public class IncidenciaNotFoundExcepcion extends RuntimeException {
    public IncidenciaNotFoundExcepcion(String message) {
        super(message);
    }

    public IncidenciaNotFoundExcepcion(Long id) {
        super("No existen incidencias con ese id");
    }

    public IncidenciaNotFoundExcepcion() {
        super("No hay incidencias para esa búsqueda");
    }
}
