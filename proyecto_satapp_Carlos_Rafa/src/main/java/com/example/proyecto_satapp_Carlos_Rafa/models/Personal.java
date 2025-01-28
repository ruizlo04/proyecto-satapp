package com.example.proyecto_satapp_Carlos_Rafa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
@SuperBuilder
public class Personal extends Usuario {

    @Enumerated(EnumType.STRING)
    private int tipo;




}
