package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id @GeneratedValue
    private Long id;

    private String nombre;
}
