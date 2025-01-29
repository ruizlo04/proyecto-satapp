package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UbicacionRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UbicacionService ubicacionService;

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

    public Incidencia save(EditIncidenciaCmd editInc) {
        Optional<Ubicacion> ubicacion = ubicacionService.findById(editInc.ubicacionId());

        return incidenciaRepository.save(Incidencia.builder()
                .fechaIncidencia(editInc.fecha())
                .titulo(editInc.titulo())
                .descripcion(editInc.descripcion())
                .urgencia(editInc.urgencia())
                .ubicacion(ubicacion.get())
                .build());
    }

    public void delete(Long id){
        incidenciaRepository.deleteById(id);
    }
}
