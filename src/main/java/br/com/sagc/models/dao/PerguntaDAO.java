package br.com.sagc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sagc.models.Pergunta;

@Repository
public interface PerguntaDAO extends JpaRepository<Pergunta, Long>{
	Pergunta findByQuestionText(String nome);
	Pergunta findByQuestionTextAndQuestionCategory(String nome, String categoria);
	List<Pergunta> findByQuestionCategory(String categoria);
}
