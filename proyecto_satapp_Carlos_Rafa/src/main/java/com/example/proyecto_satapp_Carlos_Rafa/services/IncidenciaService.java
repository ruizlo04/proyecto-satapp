package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final EquipoRepository equipoRepository;

    public List<Incidencia> findAll(){
        List <Incidencia> results = incidenciaRepository.findAll();
        if (results.isEmpty()){
            throw new EntityNotFoundException("No existen incidencias");
        }
        return results;
    }

    public Optional<Incidencia> findById(Long id){
        Optional <Incidencia> findIncidenciaOp = incidenciaRepository.findById(id);
        if (findIncidenciaOp.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado incidencia con ese ID");
        }
        return findIncidenciaOp;
    }

    public Incidencia save(EditIncidenciaCmd editInc){
        return incidenciaRepository.save(Incidencia.builder()
                        .fechaIncidencia(editInc.fechaIncidencia())
                        .titulo(editInc.titulo())
                        .descripcion(editInc.descripcion())
                        .urgencia(editInc.urgencia())
                        .equipo(equipoRepository.findById(editInc.equipoId()).orElse(null))
                .build());
    }
}
