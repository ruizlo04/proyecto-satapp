package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.EquipoNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.models.Equipo;
import com.example.proyecto_satapp_Carlos_Rafa.models.Ubicacion;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.EquipoRepository;
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
    private final UbicacionRepository ubicacionRepository;

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
        Optional<Ubicacion> ubicacion = ubicacionService.findById(editEquipo.ubicacionId());

        return equipoRepository.save(Equipo.builder()
                .nombre(editEquipo.nombre())
                .caracteristicas(editEquipo.caracteristicas())
                .ubicacion(ubicacion.get())
                .build());
    }

    public Equipo editEquipo(Long id, EditEquipoCmd editEquipo) {

        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        Optional<Ubicacion> ubicacionOptional = ubicacionService.findById(editEquipo.ubicacionId());

        if (equipoOptional.isEmpty()) {
            throw new EquipoNotFoundExcepcion("No existe el equipo con el ID especificado");
        }

        if (ubicacionOptional.isEmpty()) {
            throw new EquipoNotFoundExcepcion("No existe la ubicación con el ID especificado");
        }

        Equipo equipo = equipoOptional.get();
        equipo.setNombre(editEquipo.nombre());
        equipo.setCaracteristicas(editEquipo.caracteristicas());
        equipo.setUbicacion(ubicacionOptional.get());

        return equipoRepository.save(equipo);
    }

}
