package com.salesianos.triana.dam.EasyCar.repo;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcesionarioRepository extends JpaRepository<Concesionario, Long> {
    Page<Concesionario> findAll(Pageable pageable);

    boolean existsByNombre(String nombre);

}
