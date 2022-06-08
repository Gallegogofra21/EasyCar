package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetConcesionarioVehiculosDto {

    private Long id;
    private String nombre;
    private String direccion;
    private List<GetVehiculoDto> vehiculos;
    private Long gestorId;
}
