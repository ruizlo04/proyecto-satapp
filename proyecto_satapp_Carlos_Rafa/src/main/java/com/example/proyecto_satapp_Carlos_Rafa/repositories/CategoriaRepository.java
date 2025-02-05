package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("""
    SELECT c 
    FROM Categoria c
    LEFT JOIN FETCH c.incidencias i
    WHERE c.id = :id
    """)
    Optional<Categoria> findByIdWithIncidencias(Long id);

    @Query("""
    SELECT c 
    FROM Categoria c
    LEFT JOIN FETCH c.subcategoria s
    WHERE c.id = :id
    """)
    Optional<Categoria> findByIdWithSubcategorias(Long id);
}
