package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.models.Usuario;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        List<Usuario> result = usuarioRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay usuarios con esos criterios de busqueda");
        return result;
    }

    public Optional<Usuario> findById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontraron usuarios con ese id");
        return result;

    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

}
