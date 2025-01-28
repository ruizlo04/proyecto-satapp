package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private EquipoRepository equipoRepository;

    public List <Equipo> findAll(){
        List <Equipo> results = equipoRepository.findAll();
        if (results.isEmpty()){
            throw new EntityNotFoundException("No se han encontrado equipos");
        }
        return results;
    }
}
