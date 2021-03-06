package com.salesianos.triana.dam.EasyCar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
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
public class Concesionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String foto;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "concesionario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_CONCESIONARIO_USUARIO"))
    private Usuario usuario;

}
