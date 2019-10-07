package br.com.sagc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sagc.models.Ocorrencia;
import br.com.sagc.models.dao.OcorrenciaDAO;

@Service
public class OcorrenciaService {

	@Autowired
	OcorrenciaDAO ocorrencias;
	
	public void salvar(Ocorrencia o)
	{
		ocorrencias.save(o);
	}
	public List<Ocorrencia>buscarTodas()
	{
		return ocorrencias.findAll();
	}
	public Ocorrencia buscaPorId(Long id)
	{
		for(Ocorrencia o:ocorrencias.findAll())
		{
			if(o.getId() == id) return o;
		}
		return null;
	}
}
