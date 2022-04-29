package com.salesianos.triana.dam.EasyCar.dto.vehiculo;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Marchas;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateVehiculoDto {

    private String version;
    private LocalDate fechaMatriculacion;
    private String kilometraje;
    private String potencia;
    private Marchas marchas;
    private Double precio;
    private Tipo tipo;
    private String foto1;
    private String llantas;
    private String distribucion;
    private String procedencia;
    private String traccion;

    private Marca marca;
    private Concesionario concesionario;
}
