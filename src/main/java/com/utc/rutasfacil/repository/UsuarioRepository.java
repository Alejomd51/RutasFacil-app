package com.utc.rutasfacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rutasfacil.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsernameIgnoreCase(String username);

	Optional<Usuario> findByUsername(String username);
}
