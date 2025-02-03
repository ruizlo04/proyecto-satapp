package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query(
            """
            SELECT e
            FROM Equipo e"""
    )
    public List <Equipo> getAllEquipos();
}
