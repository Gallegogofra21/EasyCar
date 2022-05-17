package com.salesianos.triana.dam.EasyCar.dto.vehiculo;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class ConverterVehiculoDto {
    public GetVehiculoDto getVehiculoToDto(Vehiculo v) {
        return GetVehiculoDto.builder()
                .id(v.getId())
                .version(v.getVersion())
                .modelo(v.getModelo())
                .fechaMatriculacion(v.getFechaMatriculacion())
                .kilometraje(v.getKilometraje())
                .potencia(v.getPotencia())
                .marchas(v.getMarchas())
                .precio(v.getPrecio())
                .marca(v.getMarca().getId())
                .tipo(v.getTipo().getId())
                .foto1(v.getFoto1())
                .llantas(v.getLlantas())
                .distribucion(v.getDistribucion())
                .procedencia(v.getProcedencia())
                .traccion(v.getTraccion())
                .concesionario(v.getConcesionario().getId())
                .build();
    }
}
