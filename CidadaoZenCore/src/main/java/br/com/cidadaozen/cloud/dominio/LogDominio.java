package br.com.cidadaozen.cloud.dominio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Log;
import br.com.cidadaozen.cloud.repositorio.LogRepositorio;

@Service
public class LogDominio {

	@Autowired
	private LogRepositorio repositorio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Log inserirLog(Log log) throws MensagemException {
		try {
			int tamanho = 1000;
			if (log.getMensagem().length() > tamanho) {
				log.setMensagem(log.getMensagem().substring(0, tamanho));
			}

			LOG.debug("Log >> " + log);
			return repositorio.save(log);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public List<Log> consultarLogPorAplicacaoLevel(int aplicacao, int level)
			throws MensagemException {
		try {
			return repositorio.consultarLogPorAplicacaoLevel(aplicacao, level);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public List<Log> consultarLogPorAplicacaoLevelTag(int aplicacao, int level,
			String tag) throws MensagemException {
		try {
			return repositorio.consultarLogPorAplicacaoLevelTag(aplicacao,
					level, tag);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public List<Log> consultarLogPorAplicacao(int aplicacao)
			throws MensagemException {
		try {
			return repositorio.consultarLogPorAplicacao(aplicacao);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public List<Log> consultarLog() throws MensagemException {
		try {
			return repositorio.consultarLog();

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

}
