package com.salesianos.triana.dam.EasyCar.users.model;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners({AuditingEntityListener.class})
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String username;
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;
    private String avatar;
    private String password;
    private String password2;

    @OneToOne
    private Concesionario concesionario;

    @Enumerated
    private UserRole rol;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Vehiculo> vehiculosFav = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PreRemove
    public void nullearTipoDeVehiculos() {
        vehiculosFav.forEach(vehiculo -> vehiculo.setUser(null));
    }
}
