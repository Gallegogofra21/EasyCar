package com.salesianos.triana.dam.EasyCar.dto.vehiculo;

import com.salesianos.triana.dam.EasyCar.model.Marchas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class GetVehiculoSingleDto {

    private Long id;
    private String version;
    private String modelo;
    private LocalDate fechaMatriculacion;
    private String kilometraje;
    private String potencia;
    private Marchas marchas;
    private Double precio;
    private String llantas;
    private String distribucion;
    private String procedencia;
    private String traccion;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
}
