package br.com.sagc.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sagc.models.Pergunta;
import br.com.sagc.models.Resposta;

public interface RespostaDAO extends JpaRepository<Resposta, Long>{
	Resposta findByQuestion(Pergunta p);
	Resposta findByQuestionAndName(Pergunta p, String name);
}
