package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Tecnico extends Usuario{


    @ManyToMany
    @JoinTable(
            name = "tecnico_incidencia",
            joinColumns = @JoinColumn(name = "tecnico_id"),
            inverseJoinColumns = @JoinColumn(name = "incidencia_id"),
            foreignKey = @ForeignKey(name = "fk_tecnico_incidencia_tecnico"),
            inverseForeignKey = @ForeignKey(name = "fk_tecnico_incidencia_incidencia")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Incidencia> incidenciasAsignadas = new HashSet<>();

    public void addIncidenciaAsignada(Incidencia incidencia) {
        incidenciasAsignadas.add(incidencia);
        incidencia.getTecnicos().add(this);
    }

    public void removeIncidenciaAsignada(Incidencia incidencia) {
        incidenciasAsignadas.remove(incidencia);
        incidencia.getTecnicos().remove(this);
    }


}
