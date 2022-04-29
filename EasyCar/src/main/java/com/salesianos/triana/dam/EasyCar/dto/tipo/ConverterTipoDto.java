package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.model.Tipo;
import org.springframework.stereotype.Component;

@Component
public class ConverterTipoDto {
    public GetTipoDto getTipoToDto(Tipo t) {
        return GetTipoDto.builder()
                .id(t.getId())
                .nombre(t.getNombre())
                .foto(t.getFoto())
                .numVehiculos(t.getVehiculos().size())
                .build();
    }
}
