package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.Incidencia;
import com.example.proyecto_satapp_Carlos_Rafa.models.Tecnico;
import com.example.proyecto_satapp_Carlos_Rafa.models.TipoEstado;
import org.hibernate.mapping.Set;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record GetIncidenciaDto(
        LocalDateTime fechaIncidencia,
        String titulo,
        String descripcion,
        boolean urgencia,
        TipoEstado estado,
        List <GetNotaDto> notas,
        GetUsuarioDto usuario,
        List<GetTecnicoDto> tecnicos
) {

    public static GetIncidenciaDto of (Incidencia i){
        return new GetIncidenciaDto(
                i.getFechaIncidencia(),
                i.getTitulo(),
                i.getDescripcion(),
                i.isUrgencia(),
                i.getEstado(),
                i.getNotas().stream().map(GetNotaDto::of).toList(),
                GetUsuarioDto.of(i.getUsuario()),
                i.getTecnicos().stream().map(GetTecnicoDto::of).toList()
        );
    }
}
