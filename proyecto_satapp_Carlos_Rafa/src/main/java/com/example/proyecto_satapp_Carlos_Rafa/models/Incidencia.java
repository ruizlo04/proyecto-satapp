package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "incidencias")
public class Incidencia {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime fechaIncidencia;

    private String titulo;

    private String descripcion;

    private boolean urgencia;


    @Enumerated(EnumType.STRING)
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id",
                foreignKey = @ForeignKey(name = "fk_incidencia_usuario"))
    private Usuario usuario;

    @ManyToMany(mappedBy = "incidenciasAsignadas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Set<Tecnico> tecnicos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ubicacion_id",
            foreignKey = @ForeignKey(name = "fk_incidencia_ubicacion"))
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "equipo_id",
            foreignKey = @ForeignKey(name = "fk_incidencia_equipo"))
    private Equipo equipo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(
            mappedBy="incidencia",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Nota> notas = new ArrayList<>();

    public void addNota(Nota n) {
        n.setIncidencia(this);
        this.notas.add(n);
    }

    public void removeNota(Nota n) {
        this.notas.remove(n);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Incidencia that = (Incidencia) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
