package br.com.sagc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Entity
public class Pergunta {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String questionText;
	private String questionType;
	private String questionCategory;
	
	@ManyToMany
	@JoinTable(name = "pergunta_ficha", joinColumns={@JoinColumn(name="pergunta_id")},
	inverseJoinColumns={@JoinColumn(name="ficha_id")})
	private List<Ficha> ficha;
	
	@OneToMany(mappedBy="question", fetch = FetchType.LAZY)
	private List<Resposta> responses;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public List<Resposta> getResponses() {
		return responses;
	}
	public void setResponses(List<Resposta> responses) {
		this.responses = responses;
	}
	public List<Ficha> getFicha() {
		return ficha;
	}
	public void setFicha(List<Ficha> fi) {
		this.ficha = fi;
	}
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}	
	
}