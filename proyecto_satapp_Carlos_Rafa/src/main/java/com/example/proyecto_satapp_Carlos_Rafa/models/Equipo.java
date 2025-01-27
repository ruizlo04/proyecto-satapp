package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
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

    private List<String> caracteristicas;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> incidencias = new ArrayList<>();
}
