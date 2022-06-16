package com.salesianos.triana.dam.EasyCar.dto.marca;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMarcaVehiculosDto {

    private Long id;
    private String nombre;
    private List<GetVehiculoDto> vehiculos;
    private String foto;
}
