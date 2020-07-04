package br.com.IHelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.IHelp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
