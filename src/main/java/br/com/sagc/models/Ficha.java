package br.com.sagc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Repository;

@Entity
public class Ficha {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario creator;

	@ManyToMany(mappedBy="ficha")
	private List<Pergunta> questions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getCreator() {
		return creator;
	}
	public void setCreator(Usuario creator) {
		this.creator = creator;
	}
	public List<Pergunta> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Pergunta> questions) {
		this.questions = questions;
	}
	
	
}