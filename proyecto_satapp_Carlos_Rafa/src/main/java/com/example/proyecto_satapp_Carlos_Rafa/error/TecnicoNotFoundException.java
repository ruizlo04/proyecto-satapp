package com.example.proyecto_satapp_Carlos_Rafa.error;

public class TecnicoNotFoundException extends RuntimeException {
    public TecnicoNotFoundException(String message) {
        super(message);
    }

    public TecnicoNotFoundException(Long id) {
        super("No hay tecnicos con ese ID: " + id);
    }

    public TecnicoNotFoundException() {
        super("No hay tecnicos con esos requisitos de búsqueda");
    }
}
