package com.salesianos.triana.dam.EasyCar.repo;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Page<Vehiculo> findAll(Specification<Vehiculo> todos, Pageable pageable);
    List<Vehiculo> findAllByConcesionario(Concesionario concesionario);
    List<Vehiculo> findAllByMarca(Marca marca);
    List<Vehiculo> findAllByTipo(Tipo tipo);
}
