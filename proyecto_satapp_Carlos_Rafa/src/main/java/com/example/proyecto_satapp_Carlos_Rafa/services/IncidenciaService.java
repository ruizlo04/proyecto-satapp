package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.IncidenciaNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.models.*;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditNotaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetIncidenciaDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UbicacionService ubicacionService;
    private final EquipoService equipoService;
    private final UsuarioService usuarioService;

    @Transactional
    public List<GetIncidenciaDto> findAll(){

        List<Incidencia> result = incidenciaRepository.findAll();

        List<GetIncidenciaDto> result2 = result.stream().map(GetIncidenciaDto::of).toList();

        if (result2.isEmpty()){
            throw new IncidenciaNotFoundExcepcion("No existe ninguna incidencia aún");
        }
        return result2;
    }

    @Transactional
    public Incidencia findById(Long id){
        Optional <Incidencia> findIncidenciaOp = incidenciaRepository.findById(id);

        Optional<GetIncidenciaDto> result2 = findIncidenciaOp.map(GetIncidenciaDto::of);


        if (findIncidenciaOp.isEmpty()){
            throw new IncidenciaNotFoundExcepcion("No se ha encontrado incidencia con ese ID");
        }
        return findIncidenciaOp.get();
    }

    @Transactional
    public Incidencia save(EditIncidenciaCmd editInc) {

        Ubicacion ubicacion = ubicacionService.findById(editInc.ubicacionId());
        Equipo equipo = equipoService.findById(editInc.equipoId());
        Usuario usuario = usuarioService.findById(editInc.usuarioId());

        return incidenciaRepository.save(Incidencia.builder()
                .fechaIncidencia(editInc.fecha())
                .titulo(editInc.titulo())
                .descripcion(editInc.descripcion())
                .urgencia(editInc.urgencia())
                .ubicacion(ubicacion)
                .equipo(equipo)
                .usuario(usuario)
                .build());
    }

    @Transactional
    public void delete(Long id){
        incidenciaRepository.deleteById(id);
    }

    @Transactional
    public Incidencia addNotaToIncidencia(Long incidenciaId, EditNotaCmd nuevo) {
        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(incidenciaId);

        if (incidenciaOptional.isEmpty()) {
            throw new IncidenciaNotFoundExcepcion("No se ha encontrado incidencia con ese id");
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

    @Transactional
    public Incidencia removeNotaFromIncidencia(Long incidenciaId, Long notaId) {
        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(incidenciaId);

        if (incidenciaOptional.isEmpty()) {
            throw new IncidenciaNotFoundExcepcion("No se ha encontrado incidencia con ese id");
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
