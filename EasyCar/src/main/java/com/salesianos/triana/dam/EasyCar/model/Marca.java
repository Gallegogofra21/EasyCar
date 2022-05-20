package com.salesianos.triana.dam.EasyCar.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String foto;

    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();

    @PreRemove
    public void nullearMarcaDeVehiculos() {
        vehiculos.forEach(vehiculo -> vehiculo.setMarca(null));
    }
}
