package br.com.sagc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.sagc.dto.UsuarioDTO;
import br.com.sagc.models.Usuario;
import br.com.sagc.services.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService uService;
	
	@PostMapping("/fichasreqs")
	public ResponseEntity<Map<Long, Boolean>> fichasReqs(@RequestParam("userEmail") String email)
	{
		Usuario u = uService.buscaPorEmail(email);
		Map<Long, Boolean> reqs = new HashMap<Long, Boolean>();
		u.getIdsReqs().forEach((k, v)-> {
            if(v == false) reqs.put(k, v);
        });
		return new ResponseEntity<Map<Long, Boolean>>(reqs, HttpStatus.OK);
	}	
	@PostMapping("/fichasresp")
	public ResponseEntity<Map<Long, Boolean>> fichasResp(@RequestParam("userEmail") String email)
	{
		Usuario u = uService.buscaPorEmail(email);
		Map<Long, Boolean> reqs = new HashMap<Long, Boolean>();
		u.getIdsReqs().forEach((k, v)-> {
            if(v == true) reqs.put(k, v);
        });
		return new ResponseEntity<Map<Long, Boolean>>(reqs, HttpStatus.OK);
	}		
	@PostMapping("/salvarusuario")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody Usuario user) {
		
		if(uService.buscaPorEmail(user.getEmail()) != null)
		{
			System.out.println("Usuário já cadastrado no Sistema");
			return new ResponseEntity<UsuarioDTO>(HttpStatus.CONFLICT);
		}
		System.out.println("Usuário criado com sucesso!");
		if(user.getEmail().equalsIgnoreCase("admin@admin.com")) user.setOperator(true);
		uService.salvarUsuario(user);
		return new ResponseEntity<UsuarioDTO>(UsuarioDTO.transformaEmDTO(user),HttpStatus.CREATED);
    }
	@PostMapping("/allusers")
	public ResponseEntity<List<String>> buscarTodos()
	{
		List<String> lista = new ArrayList<String>();
		for(Usuario u : uService.buscaTodos())
		{
			lista.add(u.getEmail());
		}
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}
	@PostMapping("/deletarusuario")
	public ModelAndView deletar(@ModelAttribute Usuario user) {
		user = uService.buscaPorNome(user.getName());
		uService.deletarUsuario(user.getId());
		return new ModelAndView("usuarios/excluirUsuario");
	}			
	@PostMapping("/ssenha")
    public void alterarSenha(@RequestParam("userEmail") String email, @RequestParam("senha") String senha) 
	{
		Usuario user = uService.buscaPorEmail(email);
		user.setPassword(senha);
		uService.salvarUsuario(user);
		
    }		
	@PostMapping("/setoperator")
    public void alterarOperador(@RequestParam("userEmail") String email, @RequestParam("op") Boolean op) 
	{
		Usuario user = uService.buscaPorEmail(email);
		user.setOperator(op);
		uService.salvarUsuario(user);
		
    }			
	@PostMapping("/esenha")
    public ResponseEntity<UsuarioDTO> eSenhaValidacao(@RequestParam("userEmail") String email, @RequestParam("pergunta") String per, @RequestParam("resposta") String resp) {
		Usuario user = uService.buscaPorEmail(email);
		
		if(user != null)
		{
			if(user.getQuestion().equalsIgnoreCase(per) && user.getAnswer().equals(resp))
			{
				return new ResponseEntity<UsuarioDTO>(UsuarioDTO.transformaEmDTO(user), HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<UsuarioDTO>(HttpStatus.CONFLICT);
			}
		}
		else
		{
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);			
		}
    }		
	@PostMapping("/validarlogin")
    public ResponseEntity<UsuarioDTO> loginValidacao(@RequestBody Usuario user) {
		user = uService.buscaPorEmailESenha(user.getEmail(), user.getPassword());
		
		if(user != null)
		{
			return new ResponseEntity<UsuarioDTO>(UsuarioDTO.transformaEmDTO(user), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);			
		}
    }	

}
