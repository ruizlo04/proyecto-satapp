package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.*;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UbicacionRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditNotaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetIncidenciaDto;
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
    private final EquipoService equipoService;
    private final UsuarioService usuarioService;

    public List<Incidencia> findAll(){
        List <Incidencia> results = incidenciaRepository.findAll();
        if (results.isEmpty()){
            throw new EntityNotFoundException("No existe ninguna incidencia aún");
        }
        return results;
    }

    public Incidencia findById(Long id){
        Optional <Incidencia> findIncidenciaOp = incidenciaRepository.findById(id);
        if (findIncidenciaOp.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado incidencia con ese ID");
        }
        return findIncidenciaOp.get();
    }

    public Incidencia save(EditIncidenciaCmd editInc) {

        Optional<Ubicacion> ubicacion = ubicacionService.findById(editInc.ubicacionId());
        Equipo equipo = equipoService.findById(editInc.equipoId());
        Usuario usuario = usuarioService.findById(editInc.usuarioId());

        return incidenciaRepository.save(Incidencia.builder()
                .fechaIncidencia(editInc.fecha())
                .titulo(editInc.titulo())
                .descripcion(editInc.descripcion())
                .urgencia(editInc.urgencia())
                .ubicacion(ubicacion.get())
                .equipo(equipo)
                .usuario(usuario)
                .build());
    }

    public void delete(Long id){
        incidenciaRepository.deleteById(id);
    }

    public Incidencia addNotaToIncidencia(Long incidenciaId, EditNotaCmd nuevo) {
        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(incidenciaId);

        if (incidenciaOptional.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado incidencia con ese id");
        }

        Incidencia incidencia = incidenciaOptional.get();

        Nota nota = Nota.builder()
                .fecha(nuevo.fecha())
                .contenido(nuevo.contenido())
                .autor(nuevo.autor())
                .incidencia(incidencia)
                .build();

        incidencia.addNota(nota);

        return incidenciaRepository.save(incidencia);
    }

    public Incidencia removeNotaFromIncidencia(Long incidenciaId, Long notaId) {
        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(incidenciaId);

        if (incidenciaOptional.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado incidencia con ese id");
        }

        Incidencia incidencia = incidenciaOptional.get();

        Optional<Nota> notaOptional = incidenciaRepository.findNotaByIdInIncidencia(incidenciaId, notaId);

        if (notaOptional.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado la nota con ese ID en la incidencia");
        }

        incidencia.removeNota(notaOptional.get());

        return incidenciaRepository.save(incidencia);
    }



}
