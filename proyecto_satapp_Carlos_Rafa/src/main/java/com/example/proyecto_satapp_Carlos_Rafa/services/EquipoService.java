package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List <Equipo> findAll(){
        List <Equipo> results = equipoRepository.findAll();
        if (results.isEmpty())
            throw new EntityNotFoundException("No existen equipos");
        return results;
    }

    public Optional <Equipo> findById(Long id){
        Optional <Equipo> resultsOp = equipoRepository.findById(id);
        if (resultsOp.isEmpty())
            throw new EntityNotFoundException("No existen equipos para ese id");
        return resultsOp;
    }
}
