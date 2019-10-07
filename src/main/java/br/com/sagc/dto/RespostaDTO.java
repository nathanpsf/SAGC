package br.com.sagc.dto;

import br.com.sagc.models.Resposta;

public class RespostaDTO {
	private Long id;
	private String name;
	
	public static RespostaDTO transformaEmDTO(Resposta resp) {
		RespostaDTO rDTO = new RespostaDTO();
		
		rDTO.setId(resp.getId());
		rDTO.setName(resp.getName());
        return rDTO;
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
	
}
