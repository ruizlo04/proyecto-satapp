package com.example.proyecto_satapp_Carlos_Rafa.error;

public class UbicacionNotFoundExcepcion extends RuntimeException{

    public UbicacionNotFoundExcepcion(String message) {
        super(message);
    }

    public UbicacionNotFoundExcepcion(Long id) {
        super("No existen ubicaciones con ese id");
    }

    public UbicacionNotFoundExcepcion() {
        super("No hay ubicaciones para esa búsqueda");
    }
}
