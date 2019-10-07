package br.com.sagc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sagc.models.Ocorrencia;
import br.com.sagc.services.OcorrenciaService;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	OcorrenciaService oService;
	
	@PostMapping("/salvar")
	public void salvarOcorrencia(@RequestBody Ocorrencia o)
	{
		oService.salvar(o);
	}
	@GetMapping("/all")
	public List<Ocorrencia> buscaTodas()
	{
		return oService.buscarTodas();
	}
	
}
