package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query(
            """
            SELECT e
            FROM Equipo e"""
    )
    public List <Equipo> getAllEquipos();

    @Query(
            """
            SELECT e
            FROM Equipo e
            WHERE e.id = :id"""
    )
    public Optional<Equipo> getEquipoById(@Param("id")Long equipoId);

    @Query("""
            SELECT e 
            FROM Equipo e 
            WHERE e.ubicacion.id = :ubicacionId""")
    List<Equipo> findByUbicacionId(@Param("ubicacionId") Long ubicacionId);

}
