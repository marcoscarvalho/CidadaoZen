package br.com.cidadaozen.cloud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvoreRepositorio extends CrudRepository<Arvore, Long> {

	@Query(value = "select a2.* from arvore a1, arvore a2 where a1.id_arvore = a2.id and a1.id = ?1", nativeQuery = true)
	public Arvore consultarArvorePai(Long id);

	public List<Arvore> findAllByArvorePaiIsNullAndAtivoIsTrue();

	public List<Arvore> findAllByArvorePaiIsNull();
}
