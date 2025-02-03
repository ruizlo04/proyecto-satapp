package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("SELECT n FROM Incidencia i JOIN i.notas n WHERE i.id = :incidenciaId AND n.id = :notaId")
    Optional<Nota> findNotaByIdInIncidencia(@Param("incidenciaId") Long incidenciaId, @Param("notaId") Long notaId);

}
