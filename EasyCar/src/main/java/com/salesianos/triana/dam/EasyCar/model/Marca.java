package com.salesianos.triana.dam.EasyCar.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Marca implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String foto;

    @OneToMany
    private List<Vehiculo> vehiculos;
}