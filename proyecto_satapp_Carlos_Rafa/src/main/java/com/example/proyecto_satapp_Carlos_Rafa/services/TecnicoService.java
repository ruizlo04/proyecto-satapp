package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> findAll(){
        List<Tecnico> result = tecnicoRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay tecnicos con esos criterios de busqueda");
        return result;
    }

    public Optional<Tecnico> findById(Long id) {
        Optional<Tecnico> tecnicoOp = tecnicoRepository.findById(id);
        if(tecnicoOp.isEmpty())
            throw new EntityNotFoundException("No se encontraron tecnicos con ese id");
        return tecnicoOp;

    }

    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
