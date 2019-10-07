package br.com.sagc.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sagc.models.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	 Usuario findByNameAndPassword(String name, String password);
	 Usuario findByName(String name);
	 Usuario findByEmail(String email);
	 Usuario findByEmailAndPassword(String email, String password);
}