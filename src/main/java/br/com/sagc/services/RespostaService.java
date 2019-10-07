package br.com.sagc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sagc.models.Pergunta;
import br.com.sagc.models.Resposta;
import br.com.sagc.models.dao.RespostaDAO;

@Service
public class RespostaService {
	
	@Autowired
	RespostaDAO respostas;
	
	public void salvarResposta(Resposta resp)
	{
		respostas.save(resp);
	}
	public void deletarResposta(Long id)
	{
		respostas.deleteById(id);
	}
	public Resposta buscaPorPergunta(Pergunta p)
	{
		return respostas.findByQuestion(p);
	}
	public Resposta buscaPorPerguntaENome(Pergunta p, String nome)
	{
		return respostas.findByQuestionAndName(p, nome);
	}
	public Long ultimoId()
	{
		return respostas.count();
	}
}
