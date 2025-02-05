package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.IncidenciaNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.error.TecnicoNotFoundException;
import com.example.proyecto_satapp_Carlos_Rafa.models.*;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.TecnicoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditAlumnoCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditTecnicoCmd;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            throw new TecnicoNotFoundException("No hay tecnicos con esos criterios de busqueda");
        return result;
    }

    public Tecnico findById(Long id) {
        Optional<Tecnico> result = tecnicoRepository.findById(id);
        if(result.isEmpty())
            throw new TecnicoNotFoundException("No se encontró técnico con ese id");
        else {
            return result.get();
        }
    }


    public Incidencia gestionarIncidencia(Long incidenciaId, EditIncidenciaCmd incidenciaCmd) {
        Optional<Incidencia> optionalIncidencia = incidenciaRepository.findById(incidenciaId);

        if (optionalIncidencia.isEmpty()) {
            throw new IncidenciaNotFoundExcepcion("No hay incidencia con ID: " + incidenciaId);
        }

        Incidencia incidencia = optionalIncidencia.get();
        incidencia.setEstado(incidenciaCmd.estado());

        if (incidenciaCmd.estado() == TipoEstado.CERRADA) {
            incidenciaRepository.delete(incidencia);
            return null;
        }

        return incidenciaRepository.save(incidencia);
    }



    public Tecnico saveTecnico(EditTecnicoCmd editTecnicoCmd) {
        return tecnicoRepository.save(Tecnico.builder()
                .email(editTecnicoCmd.email())
                .role(editTecnicoCmd.role())
                .password(editTecnicoCmd.password())
                .username(editTecnicoCmd.username())
                .build());
    }


    public Tecnico edit(EditTecnicoCmd editTecnicoCmd, Long id) {
        Optional<Tecnico> optionalTecnico = tecnicoRepository.findById(id);

        if (optionalTecnico.isEmpty()) {
            throw new TecnicoNotFoundException(id);
        }

        Tecnico tecnico = optionalTecnico.get();
        tecnico.setUsername(editTecnicoCmd.username());
        tecnico.setPassword(editTecnicoCmd.password());
        tecnico.setEmail(editTecnicoCmd.email());
        tecnico.setRole(editTecnicoCmd.role());

        return tecnicoRepository.save(tecnico);
    }


    public void deleteById(Long id) {
        Optional<Tecnico> tecnicoOp = tecnicoRepository.findById(id);

        if (tecnicoOp.isEmpty()) {
            throw new TecnicoNotFoundException("Tecnico no encontrado");
        }

        tecnicoRepository.deleteById(id);
    }
}
