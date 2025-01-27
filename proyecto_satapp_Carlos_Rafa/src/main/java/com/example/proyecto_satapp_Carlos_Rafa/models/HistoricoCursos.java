package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(HistoricoCursosId.class)
public class HistoricoCursos {

    @Id
    @GeneratedValue
    private long id;

    private String cursoEscolar;
    private String curso;

    @Id
    @ManyToOne
    private Alumno alumno;
}
