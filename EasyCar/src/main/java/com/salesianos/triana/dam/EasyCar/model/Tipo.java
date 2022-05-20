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
public class Tipo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String foto;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();

    @PreRemove
    public void nullearTipoDeVehiculos() {
        vehiculos.forEach(vehiculo -> vehiculo.setTipo(null));
    }
}
