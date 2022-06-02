package com.salesianos.triana.dam.EasyCar.dto.vehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Marchas;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.*;

import javax.persistence.Enumerated;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateVehiculoDto {

    private String version;
    private String modelo;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaMatriculacion;
    private String kilometraje;
    private String potencia;
    @Enumerated
    private Marchas marchas;
    private Double precio;
    private Long tipo;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private String llantas;
    private String distribucion;
    private String procedencia;
    private String traccion;

    private Long marca;
    private Long concesionario;
}
