package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.EquipoNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UbicacionRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditEquipoCmd;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final UbicacionService ubicacionService;
    private final IncidenciaRepository incidenciaRepository;

    public List<Equipo> findAll(){
        List <Equipo> results = equipoRepository.getAllEquipos();
        if (results.isEmpty()){
            throw new EquipoNotFoundExcepcion("No existe ningún equipo aún");
        }
        return results;
    }

    public Equipo findById(Long id){
        Optional <Equipo> findIncidenciaOp = equipoRepository.getEquipoById(id);
        if (findIncidenciaOp.isEmpty()){
            throw new EquipoNotFoundExcepcion("No se ha encontrado equipo con ese ID");
        }
        return findIncidenciaOp.get();
    }

    public Equipo save(EditEquipoCmd editEquipo){
        Ubicacion ubicacion = ubicacionService.findById(editEquipo.ubicacionId());

        return equipoRepository.save(Equipo.builder()
                .nombre(editEquipo.nombre())
                .caracteristicas(editEquipo.caracteristicas())
                .ubicacion(ubicacion)
                .build());
    }

    public Equipo editEquipo(Long id, EditEquipoCmd editEquipo) {

        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        Ubicacion ubicacion = ubicacionService.findById(editEquipo.ubicacionId());

        if (equipoOptional.isEmpty()) {
            throw new EquipoNotFoundExcepcion("No existe el equipo con ese id");
        }

        Equipo equipo = equipoOptional.get();
        equipo.setNombre(editEquipo.nombre());
        equipo.setCaracteristicas(editEquipo.caracteristicas());
        equipo.setUbicacion(ubicacion);

        return equipoRepository.save(equipo);
    }

    public void deleteById(Long id){
        Optional <Equipo> equipoOptional = equipoRepository.findById(id);

        if (equipoOptional.isEmpty()){
            throw new EquipoNotFoundExcepcion("No se ha encontrado el equipo con ese id");
        }

        List<Incidencia> incidencias = incidenciaRepository.findByEquipoId(id);
        for (Incidencia incidencia : incidencias) {
            incidencia.setEquipo(null);
            incidenciaRepository.save(incidencia);
        }

        equipoRepository.deleteById(id);
    }

}
