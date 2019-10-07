package br.com.sagc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sagc.models.Pergunta;
import br.com.sagc.models.dao.PerguntaDAO;


@Service
public class PerguntaService {

	@Autowired
	private PerguntaDAO perguntas;
	
	public void salvarPergunta(Pergunta question)
	{
		perguntas.save(question);
	}
	public void deletarPergunta(Long id)
	{
		perguntas.deleteById(id);
	}	
	public Pergunta buscaPorNome(String nome)
	{
		return perguntas.findByQuestionText(nome);
	}
	public Pergunta bucaPorNomeECategoria(String nome, String categoria) 
	{
		return perguntas.findByQuestionTextAndQuestionCategory(nome, categoria);
	}
	public List<Pergunta> buscaPorCategoria(String cat)
	{
		return perguntas.findByQuestionCategory(cat);
	}
	public List<Pergunta> buscarTodas()
	{
		return perguntas.findAll();
	}
}
