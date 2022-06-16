package com.salesianos.triana.dam.EasyCar.repo;

import com.salesianos.triana.dam.EasyCar.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
    boolean existsByNombre(String nombre);
}
