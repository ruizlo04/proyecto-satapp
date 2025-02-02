package com.example.proyecto_satapp_Carlos_Rafa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "notas")
@IdClass(NotaPk.class)
public class Nota {

    @Id @GeneratedValue
    private Long id;

    @Id @ManyToOne @JsonIgnore
    private Incidencia incidencia;

    private String contenido;

    private String autor;
}
