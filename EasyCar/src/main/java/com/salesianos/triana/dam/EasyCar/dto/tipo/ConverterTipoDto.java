package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.model.Tipo;
import org.springframework.stereotype.Component;

@Component
public class ConverterTipoDto {
    public GetTipoDto getTipoToDto(Tipo t) {
         GetTipoDto result = GetTipoDto.builder()
                .id(t.getId())
                .nombre(t.getNombre())
                .foto(t.getFoto())
                .build();
        if(t.getVehiculos() == null) {
            result.setNumVehiculos(0);
        } else {
            result.setNumVehiculos(t.getVehiculos().size());
        }
        return result;
    }
}
