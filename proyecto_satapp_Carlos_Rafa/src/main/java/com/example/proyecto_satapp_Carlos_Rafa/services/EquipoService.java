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

    public List<Equipo> findAll(){
        List <Equipo> results = equipoRepository.findAll();
        if (results.isEmpty()){
            throw new EntityNotFoundException("No existe ningún equipo aún");
        }
        return results;
    }

    public Optional<Equipo> findById(Long id){
        Optional <Equipo> findIncidenciaOp = equipoRepository.findById(id);
        if (findIncidenciaOp.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado equipo con ese ID");
        }
        return findIncidenciaOp;
    }
}
