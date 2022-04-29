package com.salesianos.triana.dam.EasyCar.model;

import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Concesionario implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String direccion;

    @OneToMany
    private List<Vehiculo> vehiculos;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_CONCESIONARIO_USUARIO"))
    private Usuario usuario;

}
