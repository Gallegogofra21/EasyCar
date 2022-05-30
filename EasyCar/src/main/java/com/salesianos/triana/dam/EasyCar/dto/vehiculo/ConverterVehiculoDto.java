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
                .nombreMarca(v.getMarca().getNombre())
                .tipo(v.getTipo().getId())
                .foto1(v.getFoto1())
                .concesionario(v.getConcesionario().getId())
                .build();
    }

    public GetVehiculoDetails getVehiculoDetailsToDto(Vehiculo v) {
        return GetVehiculoDetails.builder()
                .id(v.getId())
                .version(v.getVersion())
                .modelo(v.getModelo())
                .fechaMatriculacion(v.getFechaMatriculacion())
                .kilometraje(v.getKilometraje())
                .potencia(v.getPotencia())
                .marchas(v.getMarchas())
                .precio(v.getPrecio())
                .nombreMarca(v.getMarca().getNombre())
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
