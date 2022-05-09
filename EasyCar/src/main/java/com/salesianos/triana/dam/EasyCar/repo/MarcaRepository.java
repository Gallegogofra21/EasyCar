package com.salesianos.triana.dam.EasyCar.repo;

import com.salesianos.triana.dam.EasyCar.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Page<Marca> findAll(Pageable pageable);
}
