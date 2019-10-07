package br.com.sagc.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.sagc.models.Pergunta;
import br.com.sagc.models.Resposta;

public class PerguntaDTO {
	private String nome;
	private String categoria;
	private String tipo;
	private List<RespostaDTO> responses;
	
	public static PerguntaDTO transformaEmDTO(Pergunta per) {
		PerguntaDTO pDTO = new PerguntaDTO();
		
		pDTO.setNome(per.getQuestionText());
		pDTO.setCategoria(per.getQuestionCategory());
		List<RespostaDTO> aux = new ArrayList<RespostaDTO>();
		for(Resposta r: per.getResponses())
		{
			aux.add(RespostaDTO.transformaEmDTO(r));
		}
		pDTO.setResponses(aux);
		pDTO.setTipo(per.getQuestionType());
        return pDTO;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<RespostaDTO> getResponses() {
		return responses;
	}

	public void setResponses(List<RespostaDTO> responses) {
		this.responses = responses;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
