package com.salesianos.triana.dam.EasyCar.dto.marca;

import com.salesianos.triana.dam.EasyCar.model.Marca;
import org.springframework.stereotype.Component;

@Component
public class ConverterMarcaDto {
    public GetMarcaDto getMarcaToDto(Marca m) {
        return GetMarcaDto.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .foto(m.getFoto())
                .numVehiculos(m.getVehiculos().size())
                .build();
    }
}
