package com.salesianos.triana.dam.EasyCar.dto.tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetTipoDto {

    private Long id;
    private String nombre;
    private String foto;
    private int numVehiculos;
}
