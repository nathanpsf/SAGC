package br.com.sagc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Repository;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	private boolean operator;
	private String question;
	private String answer;
	private String city;
	private String email;
	private Long [] idsFichas;
	
	@ElementCollection
	@CollectionTable(name="usuarios_reqs")
	@MapKeyJoinColumn(name="ficha_id")
	@Column(name="respondida")
	private Map<Long, Boolean> idsReqs = new HashMap<Long, Boolean>();
	
	@ElementCollection
	@CollectionTable(name="usuarios_content")
	@MapKeyJoinColumn(name="resp_id")
	@Column(name="conteudo")	
	private Map<Long, String> respostas = new HashMap<Long, String>();
	
	@OneToMany(mappedBy="creator")
	private List<Ficha> ficha;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isOperator() {
		return operator;
	}
	public void setOperator(boolean isOperator) {
		this.operator = isOperator;
	}
	public List<Ficha> getFicha() {
		return ficha;
	}
	public void setFicha(List<Ficha> ficha) {
		this.ficha = ficha;
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
	public Map<Long, String> getRespostas() {
		return respostas;
	}
	public void setRespostas(Map<Long, String> respostas) {
		this.respostas = respostas;
	}
	public Map<Long, Boolean> getIdsReqs() {
		return idsReqs;
	}
	public void setIdsReqs(Map<Long, Boolean> idsReqs) {
		this.idsReqs = idsReqs;
	}
}