package br.com.cidadaozen.cloud.dominio;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Parametro;
import br.com.cidadaozen.cloud.repositorio.ParametroRepositorio;

@Service
public class ParametroDominio {

	@Autowired
	private LogDominio log;

	@Autowired
	private ParametroRepositorio repositorio;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

	@SuppressWarnings("unchecked")
	@Cacheable(Constantes.CACHEABLE_PARAMETRO)
	public List<Parametro> consultarListaParametros() {
		LOGGER.debug("Consultando Lista de Parametros");
		return IteratorUtils.toList(repositorio.findAll().iterator());
	}

	@Cacheable(Constantes.CACHEABLE_PARAMETRO_NOME)
	public Parametro consultarParametro(String nome) throws MensagemException {

		LOGGER.debug("Consultando Parametro >> " + nome);
		List<Parametro> parametros = repositorio.findByNome(nome);

		if (parametros.size() > 1) {
			LOGGER.info("Lista está retornando mais de um");
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}

		for (Parametro parametro : parametros) {
			return parametro;
		}

		LOGGER.info("Dados não foram encontrados");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	@CacheEvict(allEntries = true, value = { Constantes.CACHEABLE_PARAMETRO,
			Constantes.CACHEABLE_PARAMETRO_NOME })
	public Parametro salvarParametro(Parametro parametro)
			throws MensagemException {

		try {
			LOGGER.debug("Salvando Parametro >> " + parametro);
			return repositorio.save(parametro);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	@CacheEvict(allEntries = true, value = { Constantes.CACHEABLE_PARAMETRO,
			Constantes.CACHEABLE_PARAMETRO_NOME })
	public void deletarParametro(String nome) throws MensagemException {

		Parametro parametro = consultarParametro(nome);

		if (parametro == null) {
			return;
		}

		LOGGER.debug("deletando Parametro >> " + parametro);
		repositorio.delete(parametro);
	}

}