package br.com.sagc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sagc.models.Ficha;
import br.com.sagc.models.dao.FichaDAO;


@Service
public class FichaService {
	
	@Autowired
	private FichaDAO fichas;
	
	public void salvarFicha(Ficha ficha)
	{
		fichas.save(ficha);
	}
	public void deletarFicha(Long id)
	{
		fichas.deleteById(id);
	}
	public List<Ficha> consultarTodas()
	{
		return fichas.findAll();
	}
	public Ficha buscaPorId(Long id)
	{
		for(Ficha f:fichas.findAll())
		{
			if(f.getId() == id)
			{
				return f;
			}
		}
		return null;
	}
}
