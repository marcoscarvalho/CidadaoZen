package br.com.cidadaozen.cloud.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatisfacaoRepositorio extends CrudRepository<Satisfacao, Long> {

	public List<Satisfacao> findByNome(String nome);
}
