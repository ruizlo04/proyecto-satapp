package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalRepository personalRepository;

    public List<Personal> findAll(){
        List<Personal> result = personalRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay personal con esos criterios de busqueda");
        return result;
    }

    public Optional<Personal> findById(Long id) {
        Optional<Personal> personalOp = personalRepository.findById(id);
        if(personalOp.isEmpty())
            throw new EntityNotFoundException("No se encontraron personal con ese ID");
        return personalOp;
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
