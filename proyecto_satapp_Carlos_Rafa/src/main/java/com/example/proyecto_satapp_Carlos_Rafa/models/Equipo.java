package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "equipos")
public class Equipo {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private List<String> caracteristicas = new ArrayList<>();
}
