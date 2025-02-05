package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.UbicacionNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UbicacionRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetUbicacionDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final EquipoRepository equipoRepository;

    public List <Ubicacion> findAll(){
        List <Ubicacion> results = ubicacionRepository.findAll();
        if (results.isEmpty())
            throw new UbicacionNotFoundExcepcion("No se ha encontrado ninguna ubicación");
        return results;
    }

    public Ubicacion findById(Long id){
        Optional <Ubicacion> resultsOp = ubicacionRepository.findById(id);
        if (resultsOp.isEmpty())
            throw new UbicacionNotFoundExcepcion("No se han encontrado ubicaciones con es id");
        return resultsOp.get();
    }

    public Ubicacion findByNombre(String nombre){
        Optional <Ubicacion> resultsOp = ubicacionRepository.getUbicacionByNombre(nombre);
        if (resultsOp.isEmpty())
            throw new UbicacionNotFoundExcepcion("No se han encontrado ubicaciones con ese nombre");
        return resultsOp.get();
    }

    public Ubicacion createUbicacion(GetUbicacionDto getUbicacionDto){

        return ubicacionRepository.save(Ubicacion.builder()
                .nombre(getUbicacionDto.nombre())
                .build());
    }

    public void deleteById(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isEmpty()) {
            throw new UbicacionNotFoundExcepcion("No se ha encontrado la ubicación con ese ID");
        }

        Ubicacion ubicacion = ubicacionOptional.get();

        List<Incidencia> incidencias = incidenciaRepository.findByUbicacionId(id);
        for (Incidencia incidencia : incidencias) {
            incidencia.setUbicacion(null);
            incidenciaRepository.save(incidencia);
        }

        List<Equipo> equipos = equipoRepository.findByUbicacionId(id);
        for (Equipo equipo : equipos) {
            equipo.setUbicacion(null);
            equipoRepository.save(equipo);
        }

        ubicacionRepository.deleteById(id);
    }

}
