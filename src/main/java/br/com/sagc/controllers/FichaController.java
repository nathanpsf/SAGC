package br.com.sagc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sagc.dto.PerguntaDTO;
import br.com.sagc.models.Ficha;
import br.com.sagc.models.Pergunta;
import br.com.sagc.models.Resposta;
import br.com.sagc.models.Usuario;
import br.com.sagc.services.FichaService;
import br.com.sagc.services.PerguntaService;
import br.com.sagc.services.RespostaService;
import br.com.sagc.services.UsuarioService;

@RestController
@RequestMapping("/fichas")
public class FichaController {
	
	@Autowired
	private FichaService fService;
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private PerguntaService pService;	
	
	@Autowired
	private RespostaService rService;
	
	@GetMapping("/ficha/{id}")
	public ResponseEntity<List<PerguntaDTO>> mostrarFicha(@PathVariable Long id)
	{
		Ficha f = fService.buscaPorId(id);
		List<PerguntaDTO> pers = new ArrayList<PerguntaDTO>();
		for(Pergunta p: f.getQuestions())
		{
			pers.add(PerguntaDTO.transformaEmDTO(p));
		}
		return new ResponseEntity<List<PerguntaDTO>>(pers, HttpStatus.OK);
	}		
	@PostMapping("/salvarresposta")
	public void salvarResposta(@RequestParam("user") String email, @RequestParam("nomeresp") String nomeR, 
				@RequestParam("content") String content, @RequestParam("pergunta") String pNome, 
					@RequestParam("categoria") String cNome, @RequestParam("fichaid") Long fichaid)	
	{
		Usuario u = uService.buscaPorEmail(email);
		Pergunta p = pService.bucaPorNomeECategoria(pNome, cNome);
		Resposta r;
		if(p.getQuestionType().equalsIgnoreCase("text")) 
		{
			String aux = "text";
			aux = aux.concat(p.getQuestionText());
			aux = aux.concat(Long.toString(p.getId()));			
			r = rService.buscaPorPerguntaENome(p, aux);
		}
		else
		{
			r = rService.buscaPorPerguntaENome(p, nomeR);
		}
		
		List<Usuario> us = r.getAnsweredBy();
		us.add(u);
		r.setAnsweredBy(us);
		rService.salvarResposta(r);		
		Map<Long, String> s = u.getRespostas();
		s.put(r.getId(), content);
		u.setRespostas(s);
		

		System.out.println("TA AQUIIIIIIIIIIIIIIII");
		Map<Long, Boolean> reqs = u.getIdsReqs();
		reqs.put(fichaid, true);
		uService.salvarUsuario(u);
		

	}		
	@PostMapping("/consultaresp")
	public ResponseEntity<Map<String, String>> consultaResp(@RequestParam("fichaid") Long id, @RequestParam("userEmail") String email)
	{
		Usuario u = uService.buscaPorEmail(email);
		Ficha f = fService.buscaPorId(id);
		Map<String, String> resps = new HashMap<String, String>();	
		String aux;
		String respNome = "text";
		for(Pergunta p: f.getQuestions())
		{
			if(p.getQuestionType().equalsIgnoreCase("text"))
			{
				respNome = respNome.concat(p.getQuestionText());
				respNome = respNome.concat(Long.toString(p.getId()));	
				System.out.println("BRUXOOOOOOOOOOOOOO " + respNome);
				aux = u.getRespostas().get(rService.buscaPorPerguntaENome(p, respNome).getId());
				resps.put(p.getQuestionText(), aux);
				respNome = "text";
			}
			else if(p.getQuestionType().equalsIgnoreCase("mult"))
			{
				for(Resposta r : p.getResponses())
				{
					aux = u.getRespostas().get(rService.buscaPorPerguntaENome(p, r.getName()).getId());				
					if(aux != null) 
					{
						if(resps.get(p.getQuestionText()) != null)
						{
							aux = resps.get(p.getQuestionText()).concat(",").concat(aux);
						}
						resps.put(p.getQuestionText(), aux);
					}
					
				}
			}
			else
			{
				for(Resposta r : p.getResponses())
				{
					aux = u.getRespostas().get(rService.buscaPorPerguntaENome(p, r.getName()).getId());				
					if(aux != null) 
					{
						resps.put(p.getQuestionText(), aux);
					}
					
				}				
			}
			
		}
		
		return new ResponseEntity<Map<String, String>>(resps, HttpStatus.OK);
	}	
	@PostMapping("/enviar")
	public void enviarFicha(@RequestParam("id") Long id, @RequestParam("userEmail") String email)
	{
		//int n = 0;
		Usuario u = uService.buscaPorEmail(email);
		/*if(u.getIdsFichas() == null) { n = 0; }
		else { n = u.getIdsFichas().length; } 
		Long [] ids = new Long[n+1];
		for(int i = 0; i < n; i++)
		{
			ids[i] = u.getIdsFichas()[i];
		}
		ids[n] = id;
		u.setIdsFichas(ids);*/
		Map<Long, Boolean> reqs = u.getIdsReqs();
		reqs.put(id, false);
		u.setIdsReqs(reqs);
		uService.salvarUsuario(u);
	}
	@PostMapping("/todosids")
	public ResponseEntity<Long []> consultarIds() {
		
		List<Ficha> todas = fService.consultarTodas();
		Long [] todos = new Long[todas.size()];
		int i = 0;
		for(Ficha f: todas)
		{
			todos[i] = f.getId();
			i++;
		}
		
		return new ResponseEntity<Long []>(todos, HttpStatus.OK);
	}		
	@PostMapping("/proximoid")
	public int consultarId() {
		
		List<Ficha> todas = fService.consultarTodas();
		
		return todas.size()+1;
	}	
	@PostMapping("/salvar")
	public void salvarFicha(@RequestParam("id") Long id, @RequestParam("perguntas") String[] perguntas)
	{
		Ficha fic = fService.buscaPorId(id);
		List<Pergunta> pers = fic.getQuestions();
		List <Ficha> fics;
		Pergunta aux;
		for(int i = 0; i < perguntas.length; i++)
		{
			aux = pService.buscaPorNome(perguntas[i]);
			if(aux != null)
			{
				pers.add(aux);
			}
			
		}
		fic.setQuestions(pers);
		fService.salvarFicha(fic);
		for(int i = 0; i < perguntas.length; i++)
		{
			aux = pService.buscaPorNome(perguntas[i]);
			if(aux != null)
			{
				fics = aux.getFicha();
				fics.add(fic);
				aux.setFicha(fics);
				pService.salvarPergunta(aux);
			}
			
		}		
		System.out.println("Ficha [" + id + "] salva com sucesso!" );
		
	}	
	@PostMapping("/criar")
	public Long criacaoFicha(@RequestParam("creator") String creator, @RequestParam("perguntas") String[] perguntas)
	{
		Ficha fic = new Ficha();
		List<Pergunta> pers = new ArrayList<Pergunta>();
		List <Ficha> fics;
		Pergunta aux;
		for(int i = 0; i < perguntas.length; i++)
		{
			aux = pService.buscaPorNome(perguntas[i]);
			if(aux != null)
			{
				pers.add(aux);
			}
			
		}
		fic.setQuestions(pers);
		fic.setCreator(uService.buscaPorNome(creator));
		fService.salvarFicha(fic);
		for(Pergunta p: fic.getQuestions())
		{
			fics = p.getFicha();
			fics.add(fic);
			p.setFicha(fics);
			pService.salvarPergunta(p);
		}
		return fic.getId();
		
	}

}
