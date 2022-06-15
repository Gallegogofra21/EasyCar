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
                .llantas(v.getLlantas())
                .distribucion(v.getDistribucion())
                .procedencia(v.getProcedencia())
                .traccion(v.getTraccion())
                .nombreMarca(v.getMarca().getNombre())
                .tipo(v.getTipo().getId())
                .foto1(v.getFoto1())
                .foto2(v.getFoto2())
                .foto3(v.getFoto3())
                .foto4(v.getFoto4())
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
                .foto2(v.getFoto2())
                .foto3(v.getFoto3())
                .foto4(v.getFoto4())
                .llantas(v.getLlantas())
                .distribucion(v.getDistribucion())
                .procedencia(v.getProcedencia())
                .traccion(v.getTraccion())
                .concesionario(v.getConcesionario().getId())
                .build();
    }

    public GetVehiculoSingleDto getVehiculoToSingleDto(Vehiculo v) {
        return GetVehiculoSingleDto.builder()
                .id(v.getId())
                .version(v.getVersion())
                .modelo(v.getModelo())
                .fechaMatriculacion(v.getFechaMatriculacion())
                .kilometraje(v.getKilometraje())
                .potencia(v.getPotencia())
                .marchas(v.getMarchas())
                .precio(v.getPrecio())
                .llantas(v.getLlantas())
                .distribucion(v.getDistribucion())
                .procedencia(v.getProcedencia())
                .traccion(v.getTraccion())
                .foto1(v.getFoto1())
                .foto2(v.getFoto2())
                .foto3(v.getFoto3())
                .foto4(v.getFoto4())
                .build();
    }
}
