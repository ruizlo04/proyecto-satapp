package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    @Query("""
        SELECT u
        FROM Ubicacion u
        WHERE LOWER(u.nombre) = LOWER(:nombre)
       """)
    public Optional<Ubicacion> getUbicacionByNombre(@Param("nombre") String nombre);

}
