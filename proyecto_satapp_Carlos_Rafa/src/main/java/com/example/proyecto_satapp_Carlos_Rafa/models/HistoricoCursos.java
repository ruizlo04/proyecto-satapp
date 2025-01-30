package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HistoricoCursos {
    @Id
    private String cursoEscolar;


    private String curso;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

}
