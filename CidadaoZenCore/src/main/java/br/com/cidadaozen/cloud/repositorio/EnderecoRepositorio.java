package br.com.cidadaozen.cloud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends CrudRepository<Endereco, Long> {

	@SuppressWarnings("rawtypes")
	@Query(value = "select e.bairro, count(1) qtd from avaliacao a, endereco e where a.id_endereco = e.id group by e.id", nativeQuery = true)
	public List countEnderecosBairro();

	public List<Endereco> findByBairro(String bairro);

	public List<Endereco> findByCidade(String cidade);

	public List<Endereco> findByEstado(String estado);

	public List<Endereco> findByPais(String pais);

}
