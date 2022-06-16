package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTipoVehiculosDto {

    private Long id;
    private String nombre;
    private String foto;
    private List<GetVehiculoDto> vehiculos;
}
