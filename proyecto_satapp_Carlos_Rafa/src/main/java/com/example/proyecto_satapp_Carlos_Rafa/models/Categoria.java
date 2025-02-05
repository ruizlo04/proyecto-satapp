package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Incidencia> incidencias = new ArrayList<>();

    @OneToMany (mappedBy = "padre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Categoria> subcategoria = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id",
            foreignKey = @ForeignKey(name = "fk_categoria_padre_categoria"))
    private Categoria padre;

    public void addIncidencia(Incidencia incidencia) {
        incidencia.setCategoria(this);
        this.getIncidencias().add(incidencia);
    }

    public void removeIncidencia(Incidencia incidencia) {
        this.getIncidencias().remove(incidencia);
        incidencia.setCategoria(null);
    }

    public void addSubcategoria(Categoria subcategoria) {
        subcategoria.setPadre(this);
        this.getSubcategoria().add(subcategoria);
    }

    public void removeSubcategoria(Categoria subcategoria) {
        this.getSubcategoria().remove(subcategoria);
    }
}
