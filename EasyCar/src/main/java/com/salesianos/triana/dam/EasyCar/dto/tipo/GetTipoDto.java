package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetTipoDto {

    private Long id;
    private String nombre;
    private String foto;
    private List<Vehiculo> vehiculos;
}
