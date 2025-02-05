package com.example.proyecto_satapp_Carlos_Rafa.util;

import com.example.proyecto_satapp_Carlos_Rafa.models.HistoricoCursos;

public record GetHistoricoCursoDto(
        String cursoEscolar,
        String curso
) {

    public static GetHistoricoCursoDto of(HistoricoCursos historicoCurso) {
        return new GetHistoricoCursoDto(
                historicoCurso.getCursoEscolar(),
                historicoCurso.getCurso()
        );
    }
}
