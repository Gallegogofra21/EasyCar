package com.salesianos.triana.dam.EasyCar.security.dto.vehiculo;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class ConverterVehiculoDto {
    public GetVehiculoDto getVehiculoToDto(Vehiculo v) {
        return GetVehiculoDto.builder()
                .id(v.getId())
                .version(v.getVersion())
                .fechaMatriculacion(v.getFechaMatriculacion())
                .kilometraje(v.getKilometraje())
                .potencia(v.getPotencia())
                .marchas(v.getMarchas())
                .precio(v.getPrecio())
                .marca(v.getMarca())
                .tipo(v.getTipo())
                .foto1(v.getFoto1())
                .llantas(v.getLlantas())
                .distribucion(v.getDistribucion())
                .procedencia(v.getProcedencia())
                .traccion(v.getTraccion())
                .build();
    }
}
