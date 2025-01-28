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

    private EquipoRepository equipoRepository;

    public List <Equipo> findAll(){
        List <Equipo> results = equipoRepository.findAll();
        if (results.isEmpty())
            throw new EntityNotFoundException("No se han encontrado equipos");
        return results;
    }

    public Optional <Equipo> findById (Long id){
        Optional <Equipo> findEquipoOp = equipoRepository.findById(id);
        if (findEquipoOp.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado equipo con ese ID");
        return findEquipoOp;
    }
}
