package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.TecnicoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;
    private final IncidenciaRepository incidenciaRepository;

    public List<Tecnico> findAll(){
        List<Tecnico> result = tecnicoRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay tecnicos con esos criterios de busqueda");
        return result;
    }

    public Tecnico findById(Long id) {
        Optional<Tecnico> result = tecnicoRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontró técnico con ese id");
        else {
            return result.get();
        }
    }

    public Incidencia gestionarIncidencia(Long incidenciaId, EditIncidenciaCmd incidenciaCmd) {
        return incidenciaRepository.findById(incidenciaId)
                .map(old -> {
                    old.setEstado(incidenciaCmd.estado());
                    return incidenciaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay producto con ID: "+ incidenciaId));


    }

    public Incidencia gestionarIncidencia(Long incidenciaId, Long tecnicoId, EditIncidenciaCmd incidenciaCmd) {
        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new EntityNotFoundException("No hay incidencia con ID: " + incidenciaId));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() -> new EntityNotFoundException("No hay técnico con ID: " + tecnicoId));

        incidencia.setEstado(incidenciaCmd.estado());
        tecnico.addIncidencia(incidencia);

        incidenciaRepository.save(incidencia);
        tecnicoRepository.save(tecnico);

        return incidencia;
    }




    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }

}
