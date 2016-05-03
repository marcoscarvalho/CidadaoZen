package br.com.cidadaozen.cloud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositorio extends CrudRepository<Log, Long> {

	@Query(value = "select * from log_aplicacao a order by a.data_inclusao desc limit 100", nativeQuery = true)
	public List<Log> consultarLog();

	@Query(value = "select * from log_aplicacao a where a.aplicacao = ?1 order by a.data_inclusao desc limit 100", nativeQuery = true)
	public List<Log> consultarLogPorAplicacao(int aplicacao);

	@Query(value = "select * from log_aplicacao a where a.aplicacao = ?1 and a.level = ?2 order by a.data_inclusao desc limit 100", nativeQuery = true)
	public List<Log> consultarLogPorAplicacaoLevel(int aplicacao, int level);

	@Query(value = "select * from log_aplicacao a where a.aplicacao = ?1 and a.level = ?2 and a.tag = ?3 order by a.data_inclusao desc limit 100", nativeQuery = true)
	public List<Log> consultarLogPorAplicacaoLevelTag(int aplicacao, int level,
			String tag);
}
