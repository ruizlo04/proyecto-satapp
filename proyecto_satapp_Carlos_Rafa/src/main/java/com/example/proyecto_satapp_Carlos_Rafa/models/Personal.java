package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Personal extends Usuario {

    private int tipo;

    public Personal(Long id, String username, String password, String email, String role, int tipo) {
        super(id, username, password, email, role);
        this.tipo = tipo;
    }

}
