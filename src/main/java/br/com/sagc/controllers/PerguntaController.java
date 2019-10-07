package br.com.sagc.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.sagc.dto.PerguntaDTO;
import br.com.sagc.dto.RespostaDTO;
import br.com.sagc.models.Pergunta;
import br.com.sagc.models.Resposta;
import br.com.sagc.models.Usuario;
import br.com.sagc.models.dao.PerguntaDAO;
import br.com.sagc.services.PerguntaService;
import br.com.sagc.services.RespostaService;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {
	
	@Autowired
	PerguntaService pService;
	
	@Autowired
	RespostaService rService;
	
	@PostMapping("/criar")
	public ResponseEntity<Pergunta> criacaoPergunta(@RequestBody Pergunta per)
	{
		per.setFicha(null);
		pService.salvarPergunta(per);
		if(per.getQuestionType().equalsIgnoreCase("text"))
		{
			Resposta r = new Resposta();
			r.setAnswer(false);
			r.setQuestion(per);
			String aux = "text";
			aux = aux.concat(per.getQuestionText());
			aux = aux.concat(Long.toString(pService.buscaPorNome(per.getQuestionText()).getId()));
			System.out.println("Aux::::::::::::::" + aux);
			r.setName(aux);
			r.setAnsweredBy(new ArrayList<Usuario>());
			rService.salvarResposta(r);
		}		
		return new ResponseEntity<Pergunta>(per, HttpStatus.CREATED);
	}	
	@PostMapping("/buscarcategoria")
	public ResponseEntity<Set<String>> buscaCategoria()
	{
		Set<String> categorias = new HashSet<String>();
		for(Pergunta p : pService.buscarTodas())
		{
			categorias.add(p.getQuestionCategory());
		}
		return new ResponseEntity<Set<String>>(categorias, HttpStatus.OK);
	}
	@PostMapping("/buscarperguntas")
	public ResponseEntity<Set<String>> buscaPergunta(@RequestParam("catName") String categoria)
	{
		System.out.println("Só pra saber: " + categoria);
		Set<String> perguntas = new HashSet<String>();
		for(Pergunta p : pService.buscarTodas())
		{
			if(p.getQuestionCategory().equalsIgnoreCase(categoria))
			{			
				perguntas.add(p.getQuestionText());
			}
		}
		return new ResponseEntity<Set<String>>(perguntas, HttpStatus.OK);
	}	
	@PostMapping("/consultaCategoria")
	@ResponseBody	
	public List<PerguntaDTO> validarConsultaCategoria(@RequestParam("catName") String categoria)
	{
		List<PerguntaDTO> result = new ArrayList<PerguntaDTO>();
		for(Pergunta p : pService.buscarTodas())
		{
			if(p.getQuestionCategory().equalsIgnoreCase(categoria))
			{
				result.add(PerguntaDTO.transformaEmDTO(p));
			}
		}
		return result;
	}
	@PostMapping("/consultaPergunta")
	@ResponseBody	
	public List<RespostaDTO> validarConsultaPergunta(@RequestParam("perName") String pergunta, @RequestParam("catName") String categoria)
	{
		List<RespostaDTO> result = new ArrayList<RespostaDTO>();
		for(Pergunta p : pService.buscarTodas())
		{
			if(p.getQuestionCategory().equalsIgnoreCase(categoria) && p.getQuestionText().equalsIgnoreCase(pergunta))
			{
				for(Resposta resp: p.getResponses())
				{
					result.add(RespostaDTO.transformaEmDTO(resp));
				}
			}
		}
		return result;
	}	

}
