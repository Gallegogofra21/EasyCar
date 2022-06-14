package com.salesianos.triana.dam.EasyCar.users.repo;

import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
