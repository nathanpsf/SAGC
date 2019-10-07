package br.com.sagc.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sagc.models.Ocorrencia;

@Repository
public interface OcorrenciaDAO extends JpaRepository<Ocorrencia, Long> {

}
