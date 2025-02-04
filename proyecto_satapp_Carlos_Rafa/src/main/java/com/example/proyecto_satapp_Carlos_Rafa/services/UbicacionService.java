package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public List <Ubicacion> findAll(){
        List <Ubicacion> results = ubicacionRepository.findAll();
        if (results.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado ninguna ubicación");
        return results;
    }

    public Optional <Ubicacion> findById(Long id){
        Optional <Ubicacion> resultsOp = ubicacionRepository.findById(id);
        if (resultsOp.isEmpty())
            throw new EntityNotFoundException("No se han encontrado ubicaciones con es id");
        return resultsOp;
    }

    public Optional <Ubicacion> findByNombre(String nombre){
        Optional <Ubicacion> resultsOp = ubicacionRepository.getUbicacionByNombre(nombre);
        if (resultsOp.isEmpty())
            throw new EntityNotFoundException("No se han encontrado ubicaciones con ese nombre");
        return resultsOp;
    }
}
