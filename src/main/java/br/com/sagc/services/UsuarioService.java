package br.com.sagc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sagc.models.Usuario;
import br.com.sagc.models.dao.UsuarioDAO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarios;
	
	public void salvarUsuario(Usuario user)
	{
		usuarios.save(user);
	}
	public void deletarUsuario(Long id)
	{
		usuarios.deleteById(id);
	}	
	public Usuario buscaPorNome(String nome)
	{
		return usuarios.findByName(nome);
	}	
	public Usuario buscaPorEmail(String email)
	{
		return usuarios.findByEmail(email);
	}
	public Usuario buscaPorNomeESenha(String nome, String senha)
	{
		return usuarios.findByNameAndPassword(nome, senha);
	}
	public Usuario buscaPorEmailESenha(String email, String senha)
	{
		return usuarios.findByEmailAndPassword(email, senha);
	}	
	public List<Usuario> buscaTodos()
	{
		return usuarios.findAll();
	}
}
