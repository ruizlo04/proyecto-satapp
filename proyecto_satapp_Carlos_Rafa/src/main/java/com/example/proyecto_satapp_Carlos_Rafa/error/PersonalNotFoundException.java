package com.example.proyecto_satapp_Carlos_Rafa.error;

public class PersonalNotFoundException extends RuntimeException {
    public PersonalNotFoundException(String message) {
        super(message);
    }

    public PersonalNotFoundException(Long id) {
        super("No hay personal con ese ID: " + id);
    }
    public PersonalNotFoundException() {
        super("No hay personal con esos requisitos de búsqueda");
    }
}
