package com.example.proyecto_satapp_Carlos_Rafa.services;

import com.example.proyecto_satapp_Carlos_Rafa.error.CategoriaNotFoundException;
import com.example.proyecto_satapp_Carlos_Rafa.error.IncidenciaNotFoundExcepcion;
import com.example.proyecto_satapp_Carlos_Rafa.models.Categoria;
import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.CategoriaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.repositories.IncidenciaRepository;
import com.example.proyecto_satapp_Carlos_Rafa.util.EditCategoriaCmd;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final IncidenciaRepository incidenciaRepository;

    @Transactional
    public List<Categoria> findAll(){
        List<Categoria> result = categoriaRepository.findAll();
        if(result.isEmpty())
            throw new CategoriaNotFoundException("No existe ninguna categoría");

        result.forEach(categoria -> {
            categoriaRepository.findByIdWithIncidencias(categoria.getId())
                    .ifPresent(c -> categoria.setIncidencias(c.getIncidencias()));

            categoriaRepository.findByIdWithSubcategorias(categoria.getId())
                    .ifPresent(c -> categoria.setSubcategoria(c.getSubcategoria()));
        });

        return result;
    }

    @Transactional
    public Categoria findById(Long id){
        Optional<Categoria> categoriaOp = categoriaRepository.findByIdWithIncidencias(id);

        if (categoriaOp.isEmpty()) {
            throw new CategoriaNotFoundException("No existe categoría con ese id");
        }
        Categoria categoria = categoriaOp.get();

        Optional<Categoria> categoriaConSubcategorias = categoriaRepository.findByIdWithSubcategorias(id);
        categoriaConSubcategorias.ifPresent(c -> categoria.setSubcategoria(c.getSubcategoria()));

        if (categoria.getIncidencias().isEmpty()) {
            throw new IncidenciaNotFoundExcepcion("No existen incidencias para la categoría con id");
        }

        return categoria;
    }


    @Transactional
    public Categoria save(EditCategoriaCmd editCategoriaCmd){

        return categoriaRepository.save(Categoria.builder()
                .nombre(editCategoriaCmd.nombre())
                .build());

    }

    @Transactional
    public Categoria edit(Long id, EditCategoriaCmd editCategoriaCmd) {
        Optional<Categoria> categoriaOp = categoriaRepository.findByIdWithIncidencias(id);

        if (categoriaOp.isEmpty()) {
            throw new CategoriaNotFoundException("No se ha encontrado esa categoría");
        }

        Categoria categoria = categoriaOp.get();

        categoria.setNombre(editCategoriaCmd.nombre());

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Categoria> categoriaOp = categoriaRepository.findByIdWithIncidencias(id);

        if (categoriaOp.isEmpty()) {
            throw new CategoriaNotFoundException("No existe categoría con ese ID");
        }

        Categoria categoria = categoriaOp.get();

        incidenciaRepository.deleteAll(categoria.getIncidencias());

        categoriaRepository.deleteById(id);
    }


    @Transactional
    public Categoria addIncidencia(Long idCategoria, Long idIncidencia) {
        Optional<Categoria> categoria = categoriaRepository.findByIdWithIncidencias(idCategoria);

        if (categoria.isEmpty())
            throw new CategoriaNotFoundException("No hay categoria con id");

        Optional<Incidencia> incidencia = incidenciaRepository.findById(idIncidencia);

        if (incidencia.isEmpty())
            throw new IncidenciaNotFoundExcepcion("No hay incidencia con id");

        categoria.get().addIncidencia(incidencia.get());
        return categoriaRepository.save(categoria.get());
    }


    @Transactional
    public Categoria removeIncidencia (Long idCategoria, Long idIncidencia){
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

        if(categoria.isEmpty())
            throw new CategoriaNotFoundException("No hay categoria con id");

        Optional<Incidencia> incidencia = incidenciaRepository.findById(idIncidencia);

        if(incidencia.isEmpty())
            throw new IncidenciaNotFoundExcepcion("No hay incidencia con id");

        categoria.get().removeIncidencia(incidencia.get());
        return categoriaRepository.save(categoria.get());
    }

    @Transactional
    public Categoria saveSubcategoria (Long idCategoria, EditCategoriaCmd saveSubcategoriaCmd){
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

        if (categoria.isEmpty())
            throw new CategoriaNotFoundException("No hay categoria con id");

        Categoria nuevaCategoria = Categoria.builder()
                .nombre(saveSubcategoriaCmd.nombre())
                .build();

        categoria.get().addSubcategoria(nuevaCategoria);

        return categoriaRepository.save(nuevaCategoria);

    }

    @Transactional
    public Categoria deleteSubcategoria (Long idCategoria, Long idSubcategoria){
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

        if (categoria.isEmpty())
            throw new CategoriaNotFoundException("No hay categoria con id");

        Optional <Categoria> subcategoria = categoriaRepository.findById(idSubcategoria);

        if(subcategoria.isEmpty())
            throw new CategoriaNotFoundException("No hay subcategorias con id");

        categoria.get().removeSubcategoria(subcategoria.get());

        return categoriaRepository.save(categoria.get());
    }

    @Transactional
    public Categoria addToSubcategoria (Long idPadre, Long idSubcategoria){
        Optional <Categoria> categoria = categoriaRepository.findById(idPadre);
        Optional <Categoria> subcategoria = categoriaRepository.findById(idSubcategoria);

        if(categoria.isEmpty())
            throw new CategoriaNotFoundException("No hay categoria con id");

        if(subcategoria.isEmpty())
            throw new CategoriaNotFoundException("No hay subcategoria con id");

        categoria.get().addSubcategoria(subcategoria.get());

        return categoriaRepository.save(categoria.get());
    }

}
