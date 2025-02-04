package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.PersonalRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditAlumnoCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditPersonalCmd;
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

    public Personal findById(Long id) {
        Optional<Personal> result = personalRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontró personal con ese id");
        else {
            return result.get();
        }
    }

    public Personal savePersonal(EditPersonalCmd editPersonalCmd) {
        return personalRepository.save(Personal.builder()
                .email(editPersonalCmd.email())
                .role(editPersonalCmd.role())
                .password(editPersonalCmd.password())
                .username(editPersonalCmd.username())
                        .tipo(editPersonalCmd.tipo())
                .build());
    }

    public Personal edit(EditPersonalCmd editPersonalCmd, Long id) {
        return personalRepository.findById(id)
                .map(old -> {
                    old.setUsername(editPersonalCmd.username());
                    old.setPassword(editPersonalCmd.password());
                    old.setEmail(editPersonalCmd.email());
                    old.setRole(editPersonalCmd.role());
                    old.setTipo(editPersonalCmd.tipo());
                    return personalRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay personal con ID: "+ id));

    }

    public void deleteById(Long id) {
        Optional<Personal> personalOp = personalRepository.findById(id);

        if (personalOp.isEmpty()) {
            throw new EntityNotFoundException("Personal no encontrado");
        }

        personalRepository.deleteById(id);
    }}
