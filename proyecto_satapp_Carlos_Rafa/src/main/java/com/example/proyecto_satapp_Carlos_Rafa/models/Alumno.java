package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private List<HistoricoCursos> historicoCursosList = new ArrayList<>();


    // MÉTODOS HELPER

    public void addHistoricoCursos(HistoricoCursos hc) {
        hc.setAlumno(this);
        this.historicoCursosList.add(hc);
    }

    public void removeHistoricoCursos(HistoricoCursos hc) {
        this.historicoCursosList.remove(hc);

    }

}
