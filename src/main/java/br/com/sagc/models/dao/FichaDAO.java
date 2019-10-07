package br.com.sagc.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sagc.models.Ficha;

@Repository
public interface FichaDAO extends JpaRepository<Ficha, Long> {

}
