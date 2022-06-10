package com.salesianos.triana.dam.EasyCar.repo;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Page<Vehiculo> findAll(Specification<Vehiculo> todos, Pageable pageable);
    List<Vehiculo> findAllByConcesionario(Concesionario concesionario);
    List<Vehiculo> findAllByMarca(Marca marca);

    @Query(value = """
            SELECT *
            FROM VEHICULO v JOIN MARCA m ON (m.ID = v.MARCA_ID)
            WHERE m.NOMBRE = :nombre
            """, nativeQuery = true)
    Page<Vehiculo> findAllVehiculosByMarca (@Param("nombre") String nombre, Pageable pageable);

    @Query(value = """
            SELECT *
            FROM VEHICULO v JOIN TIPO t ON (t.ID = v.TIPO_ID)
            WHERE t.ID = :id
            """, nativeQuery = true)
    Page<Vehiculo> findAllVehiculosByTipo (@Param("id") Long id, Pageable pageable);
    List<Vehiculo> findAllByTipo(Tipo tipo);
}
