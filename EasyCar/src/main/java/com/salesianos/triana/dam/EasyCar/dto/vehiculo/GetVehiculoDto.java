package com.salesianos.triana.dam.EasyCar.dto.vehiculo;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Marchas;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class GetVehiculoDto {

    private Long id;
    private String version;
    private LocalDate fechaMatriculacion;
    private String kilometraje;
    private String potencia;
    private Marchas marchas;
    private Double precio;
    private Marca marca;
    private Tipo tipo;
    private String foto1;
    private String llantas;
    private String distribucion;
    private String procedencia;
    private String traccion;
    private Concesionario concesionario;
}
