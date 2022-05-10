package com.salesianos.triana.dam.EasyCar.model;

import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String version;
    private LocalDate fechaMatriculacion;
    private String kilometraje;
    private String potencia;
    @Enumerated
    private Marchas marchas;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "concesionario_id", foreignKey = @ForeignKey(name = "FK_VEHICULO_CONCESIONARIO"))
    private Concesionario concesionario;

    @ManyToOne
    @JoinColumn(name = "marca_id", foreignKey = @ForeignKey(name = "FK_VEHICULO_MARCA"))
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "tipo_id", foreignKey = @ForeignKey(name = "FK_VEHICULO_TIPO"))
    private Tipo tipo;

    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private String llantas;
    private String distribucion;
    private String procedencia;
    private String traccion;

    // HELPERS

    public void addToConcesionario(Concesionario c) {
        concesionario = c;
        if (c.getVehiculos() == null) {
            c.setVehiculos(new ArrayList<>());
            c.getVehiculos().add(this);
        }
    }

    public void removeFromConcesionario(Concesionario c) {
        c.getVehiculos().remove(this);
        concesionario = null;
    }
}
