package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.ConverterVehiculoDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.users.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterConcesionarioDto {

    private ConverterVehiculoDto converterVehiculoDto;

    public GetConcesionarioDto getConcesionarioToDto(Concesionario c) {
         GetConcesionarioDto result = GetConcesionarioDto.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .direccion(c.getDireccion())
                .usuarioId(c.getUsuario().getId())
                .build();
        if(c.getVehiculos() == null) {
            result.setNumVehiculos(0);
        } else {
            result.setNumVehiculos(c.getVehiculos().size());
        }
        return result;
    }
}
