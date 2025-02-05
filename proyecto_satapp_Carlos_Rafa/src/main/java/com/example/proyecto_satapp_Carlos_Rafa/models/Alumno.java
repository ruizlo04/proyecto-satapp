package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Alumno extends Usuario {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(
            mappedBy = "alumno",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HistoricoCursos> historicoCursos= new ArrayList<>();

    public void addHistoricoCursos(HistoricoCursos historicoCursos) {
        historicoCursos.setAlumno(this);
        this.historicoCursos.add(historicoCursos);
    }

    public void removeHistoricoCursos(HistoricoCursos historicoCursos) {
        this.historicoCursos.remove(historicoCursos);
        historicoCursos.setAlumno(null);
    }
}