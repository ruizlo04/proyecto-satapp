package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public List<Alumno> findAll(){
        List<Alumno> result = alumnoRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay alumnos con esos criterios de busqueda");
        return result;
    }

    public Optional<Alumno> findById(Long id) {
        Optional<Alumno> alumnoOp = alumnoRepository.findById(id);
        if(alumnoOp.isEmpty())
            throw new EntityNotFoundException("No se encontraron alumnos con ese ID");
        return alumnoOp;
    }

    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }

}
