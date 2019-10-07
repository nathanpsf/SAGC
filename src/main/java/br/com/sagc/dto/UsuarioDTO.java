package br.com.sagc.dto;

import java.util.List;
import java.util.Set;

import br.com.sagc.models.Usuario;

public class UsuarioDTO {
	private Long id;
	private String name;
	private boolean operator;
	private String question;
	private String answer;
	private String city;
	private String email;
	private Long [] idsFichas;
	
	public static UsuarioDTO transformaEmDTO(Usuario user) {
		UsuarioDTO uDTO = new UsuarioDTO();
		
		uDTO.setId(user.getId());
		uDTO.setName(user.getName());
		uDTO.setOperator(user.isOperator());
		uDTO.setQuestion(user.getQuestion());
		uDTO.setAnswer(user.getAnswer());
		uDTO.setCity(user.getCity());
		uDTO.setEmail(user.getEmail());
		user.setIdsFichas(user.getIdsFichas());
        return uDTO;
    }	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOperator() {
		return operator;
	}
	public void setOperator(boolean operator) {
		this.operator = operator;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long [] getIdsFichas() {
		return idsFichas;
	}
	public void setIdsFichas(Long [] idsFichas) {
		this.idsFichas = idsFichas;
	}

}
