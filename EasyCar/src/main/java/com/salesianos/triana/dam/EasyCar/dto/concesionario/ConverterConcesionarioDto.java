package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import org.springframework.stereotype.Component;

@Component
public class ConverterConcesionarioDto {
    public GetConcesionarioDto getConcesionarioToDto(Concesionario c) {
        return GetConcesionarioDto.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .direccion(c.getDireccion())
                .numVehiculos(c.getVehiculos().size())
                .usuario(c.getUsuario())
                .build();
    }
}
