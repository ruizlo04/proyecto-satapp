package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "usuario",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
            )
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> incidencias = new ArrayList<>();

    public void addIncidencia(Incidencia incidencia) {
        incidencia.setUsuario(this);
        this.incidencias.add(incidencia);
    }

    public void removeIncidencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
    }



    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario  = (Usuario) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}