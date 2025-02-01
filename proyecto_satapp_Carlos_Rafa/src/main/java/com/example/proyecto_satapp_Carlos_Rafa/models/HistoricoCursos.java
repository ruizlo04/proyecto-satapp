package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class HistoricoCursos {
    @Id
    @GeneratedValue
    private Long id;


    private String cursoEscolar;


    private String curso;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

}
