package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Alumno;
import com.example.proyecto_satapp_Carlos_Rafa.models.HistoricoCursos;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.AlumnoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditAlumnoCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditHistoricoCursoCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditUsuarioCmd;
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

    public Alumno findById(Long id) {
        Optional<Alumno> result = alumnoRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontró alumno con ese id");
        else {
            return result.get();
        }
    }

    public Alumno saveAlumno(EditAlumnoCmd editAlumnoDto) {
        return alumnoRepository.save(Alumno.builder()
                .email(editAlumnoDto.email())
                .role(editAlumnoDto.role())
                .password(editAlumnoDto.password())
                .username(editAlumnoDto.username())
                .historicoCursos(editAlumnoDto.historicoCursos())
                .build());
    }


    public HistoricoCursos saveHistoricoCurso(Long alumnoId, EditHistoricoCursoCmd editHistoricoCursoCmd) {
        Alumno alumno = alumnoRepository.findById(alumnoId).get();
        HistoricoCursos historicoCursos = HistoricoCursos.builder()
                .curso(editHistoricoCursoCmd.curso())
                .cursoEscolar(editHistoricoCursoCmd.cursoEscolar())
                .alumno(alumno)
                .build();
        alumno.getHistoricoCursos().add(historicoCursos);
        alumnoRepository.save(alumno);
        return historicoCursos;
    }

    public Alumno edit(EditAlumnoCmd editAlumnoCmd, Long id) {
        return alumnoRepository.findById(id)
                .map(old -> {
                    old.setUsername(editAlumnoCmd.username());
                    old.setPassword(editAlumnoCmd.password());
                    old.setEmail(editAlumnoCmd.email());
                    old.setRole(editAlumnoCmd.role());
                    return alumnoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay alumno con ID: "+ id));

    }


    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }

}
