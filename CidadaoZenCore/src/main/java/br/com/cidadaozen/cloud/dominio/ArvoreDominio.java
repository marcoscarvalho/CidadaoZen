package br.com.cidadaozen.cloud.dominio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Arvore;
import br.com.cidadaozen.cloud.repositorio.ArvoreRepositorio;

@Service
public class ArvoreDominio {

	@Autowired
	private ArvoreRepositorio arvoreRepositorio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Arvore consultarArvore(long id) throws MensagemException {
		if (arvoreRepositorio.exists(id)) {
			LOG.debug("retornando id >> " + id);
			return arvoreRepositorio.findOne(id);
		}

		LOG.info("id " + id + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	public Arvore consultarArvorePai(long id) throws MensagemException {

		if (arvoreRepositorio.exists(id)) {
			LOG.debug("retornando id >> " + id);
			return arvoreRepositorio.consultarArvorePai(id);
		}

		LOG.info("id " + id + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	public List<Arvore> consultarArvores() throws MensagemException {

		try {
			LOG.debug("consultando arvores");
			return arvoreRepositorio.findAllByArvorePaiIsNullAndAtivoIsTrue();

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO, e);
		}
	}

	public Arvore salvarArvore(Arvore arvore) throws MensagemException {

		LOG.debug("Inicio salvarArvore >> " + arvore);
		validarArvore(arvore);

		if (arvore.getArvorePai() != null) {
			Arvore arvorePai = consultarArvore(arvore.getArvorePai().getId());
			arvore.setArvorePai(arvorePai);
		}

		try {
			LOG.debug("salvando arvore >> " + arvore);
			return arvoreRepositorio.save(arvore);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO, e);
		}
	}

	private void validarArvore(Arvore arvore) throws MensagemException {
		if (null == arvore.getParametro()
				|| null == arvore.getParametro().getNome()
				|| 0L == arvore.getParametro().getId()) {
			LOG.info("Avore sem informações de Parametro >> " + arvore);
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}
	}

	public void ativarArvore(long id, boolean ativar) throws MensagemException {
		Arvore arvore = consultarArvore(id);

		LOG.debug("id e ativar >> " + id + " " + ativar);
		if (ativar)
			arvore.setAtivo(true);
		else
			arvore.setAtivo(false);

		salvarArvore(arvore);
	}

	public void deletarArvore(long id) throws MensagemException {

		Arvore arvore = null;
		try {
			LOG.debug("deletando arvore >> " + id);
			arvore = arvoreRepositorio.findOne(id);

			if (arvore == null)
				return;

			LOG.debug("delete");
			arvoreRepositorio.delete(arvore);

		} catch (Exception e) {
			LOG.info("Não foi possível deletar a árvore >> " + id);
			desativarArvore(arvore);
		}
	}

	private void desativarArvore(Arvore arvore) throws MensagemException {
		try {
			arvore.setAtivo(false);
			salvarArvore(arvore);

		} catch (MensagemException e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO, e);
		}
	}

}
