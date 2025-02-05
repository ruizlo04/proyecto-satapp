package com.example.proyecto_satapp_Carlos_Rafa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private Incidencia incidencia;
    private Long id;
}
