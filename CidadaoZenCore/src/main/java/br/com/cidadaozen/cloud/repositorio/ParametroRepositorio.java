package br.com.cidadaozen.cloud.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepositorio extends CrudRepository<Parametro, Long> {

	public List<Parametro> findByNome(String nome);

}
