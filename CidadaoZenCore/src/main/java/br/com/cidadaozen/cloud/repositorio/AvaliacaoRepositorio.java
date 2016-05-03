package br.com.cidadaozen.cloud.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepositorio extends CrudRepository<Avaliacao, Long> {

	public List<Avaliacao> findByDataInclusaoGreaterThan(Date dataInclusao);

	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT * FROM COUNT_ARVORE WHERE ID = ?1 and bairro = ?2", nativeQuery = true)
	public List countAvaliacoes(Long idArvore, String bairro);

	public List<Avaliacao> findByEndereco(Endereco endereco);

	public List<Avaliacao> findByUsuario(Usuario usuario);

}
