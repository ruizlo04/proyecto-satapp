package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    @Query(
            """
            SELECT u
            FROM Ubicacion u"""
    )
    public List<Ubicacion> getAllUbicaciones();
}
