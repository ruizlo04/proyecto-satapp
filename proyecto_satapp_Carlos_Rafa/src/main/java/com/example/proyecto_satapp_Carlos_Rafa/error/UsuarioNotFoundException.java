package com.example.proyecto_satapp_Carlos_Rafa.error;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }

  public UsuarioNotFoundException(Long id) {
    super("No hay usuarios con ese ID: " + id);
  }
  public UsuarioNotFoundException() {
    super("No hay usuarios con esos requisitos de búsqueda");
  }
}
