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

    private List<String> caracteristicas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", foreignKey = @ForeignKey(name = "fk_equipo_ubicacion"))
    private Ubicacion ubicacion;
}
