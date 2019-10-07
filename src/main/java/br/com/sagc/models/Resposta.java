package br.com.sagc.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Entity
public class Resposta {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    @ManyToMany
    @JoinTable(name="resposta_usuario", joinColumns=
    {@JoinColumn(name="resposta_id")}, inverseJoinColumns=
      {@JoinColumn(name="usuario_id")})
	private List<Usuario> answeredBy;
	
	@ManyToOne
	private Pergunta question;
	
	private String name;
	
	private boolean answer = false;
	
	private String content;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pergunta getQuestion() {
		return question;
	}
	public void setQuestion(Pergunta question) {
		this.question = question;
	}
	public boolean isAnswer() {
		return answer;
	}
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Usuario> getAnsweredBy() {
		return answeredBy;
	}
	public void setAnsweredBy(List<Usuario> answeredBy) {
		this.answeredBy = answeredBy;
	}	
	
}