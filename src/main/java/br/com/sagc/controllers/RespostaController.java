package br.com.sagc.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sagc.dto.RespostaDTO;
import br.com.sagc.models.Resposta;
import br.com.sagc.models.Usuario;
import br.com.sagc.services.PerguntaService;
import br.com.sagc.services.RespostaService;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	
	@Autowired
	RespostaService rService;
	
	@Autowired
	PerguntaService pService;
	
	@PostMapping("/criar")
	@ResponseBody
	public ResponseEntity<RespostaDTO> respostaCriacao(@RequestBody Resposta resp)
	{
		resp.setAnsweredBy(new ArrayList<Usuario>());
		resp.setAnswer(false);
		rService.salvarResposta(resp);
		return new ResponseEntity<RespostaDTO>(RespostaDTO.transformaEmDTO(resp),HttpStatus.CREATED);
	}	
}
