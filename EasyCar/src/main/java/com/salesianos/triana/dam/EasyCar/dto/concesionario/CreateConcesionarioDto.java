package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameConcesionario;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Value
@Builder
public class CreateConcesionarioDto {

    @NotBlank(message = "{concesionario.nombre.blank}")
    //@UniqueNameConcesionario(message = "{concesionario.nombre.unico}")
    private String nombre;
    private String direccion;
    private String foto;
    private List<Vehiculo> vehiculos;
    private Long usuarioId;
}
