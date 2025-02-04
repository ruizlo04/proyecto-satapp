package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UsuarioRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditIncidenciaCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditUsuarioCmd;
import com.example.proyecto_satapp_Carlos_Rafa.util.GetIncidenciaDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final IncidenciaRepository incidenciaRepository;

    public List<Usuario> findAll(){
        List<Usuario> result = usuarioRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay usuarios con esos criterios de busqueda");
        return result;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontraron usuarios con ese id");
        else {
            return result.get();
        }
    }

    public Incidencia abrirIncidencia(Long usuarioId, EditIncidenciaCmd incidenciaCmd) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

        if (optionalUsuario.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        Incidencia incidencia =  Incidencia.builder()
                .fechaIncidencia(incidenciaCmd.fecha())
                .titulo(incidenciaCmd.titulo())
                .descripcion(incidenciaCmd.descripcion())
                .urgencia(incidenciaCmd.urgencia())
                .estado(incidenciaCmd.estado())
                .usuario(optionalUsuario.get())
                .build();

        incidenciaRepository.save(incidencia);

        optionalUsuario.get().addIncidencia(incidencia);

        usuarioRepository.save(optionalUsuario.get());

        return incidencia;
    }

    public Usuario save(EditUsuarioCmd nuevo) {
        return usuarioRepository.save(Usuario.builder()
                .username(nuevo.username())
                .password(nuevo.password())
                .email(nuevo.email())
                .role(nuevo.role())
                .build());
    }


    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

}
