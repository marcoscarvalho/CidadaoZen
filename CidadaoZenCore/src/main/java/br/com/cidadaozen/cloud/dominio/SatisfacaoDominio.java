package br.com.cidadaozen.cloud.dominio;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Satisfacao;
import br.com.cidadaozen.cloud.repositorio.SatisfacaoRepositorio;

@Service
public class SatisfacaoDominio {

	@Autowired
	private LogDominio log;

	@Autowired
	private SatisfacaoRepositorio repositorio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@Cacheable(Constantes.CACHEABLE_SATISFACAO)
	@SuppressWarnings("unchecked")
	public List<Satisfacao> consultarListaSatisfacao() {
		LOG.debug("Consultando lista de satisfacao");
		return IteratorUtils.toList(repositorio.findAll().iterator());
	}

	@Cacheable(Constantes.CACHEABLE_SATISFACAO_NOME)
	public Satisfacao consultarSatisfacao(String nome) throws MensagemException {

		LOG.debug("Consultando satisfacao >> " + nome);
		Collection<Satisfacao> satisfacaos = repositorio.findByNome(nome);

		if (satisfacaos.size() > 1) {
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}

		for (Satisfacao satisfacao : satisfacaos) {
			return satisfacao;
		}

		LOG.info("nome " + nome + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	@CacheEvict(allEntries = true, value = { Constantes.CACHEABLE_SATISFACAO,
			Constantes.CACHEABLE_SATISFACAO_NOME })
	public Satisfacao salvarSatisfacao(Satisfacao satisfacao)
			throws MensagemException {

		try {
			LOG.info("Salvando satisfacao >> " + satisfacao);
			return repositorio.save(satisfacao);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	@CacheEvict(allEntries = true, value = { Constantes.CACHEABLE_SATISFACAO,
			Constantes.CACHEABLE_SATISFACAO_NOME })
	public void deletarSatisfacao(String nome) throws MensagemException {

		LOG.debug("Tentando deletar satisfacao >> " + nome);
		Satisfacao satisfacao = consultarSatisfacao(nome);

		if (satisfacao == null) {
			return;
		}

		LOG.info("Deletando satisfacao >> " + satisfacao);
		repositorio.delete(satisfacao);
	}

}