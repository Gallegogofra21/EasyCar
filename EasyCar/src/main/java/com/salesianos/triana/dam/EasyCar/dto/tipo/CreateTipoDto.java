package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameTipo;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class CreateTipoDto {
    @NotBlank(message = "{tipo.nombre.blank}")
    @UniqueNameTipo(message = "{tipo.nombre.unico}")
    private String nombre;
    private String foto;
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();
}
