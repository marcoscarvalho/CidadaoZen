package br.com.cidadaozen.cloud.dominio;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Avaliacao;
import br.com.cidadaozen.cloud.repositorio.AvaliacaoRepositorio;
import br.com.cidadaozen.cloud.repositorio.Endereco;
import br.com.cidadaozen.cloud.repositorio.Satisfacao;
import br.com.cidadaozen.cloud.repositorio.Usuario;

@Service
public class AvaliacaoDominio {

	@Autowired
	private AvaliacaoRepositorio repositorio;

	@Autowired
	private SatisfacaoDominio satisfacaoDominio;

	@Autowired
	private EnderecoDominio enderecoDominio;

	@Autowired
	private ArvoreDominio arvoreDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@SuppressWarnings("unchecked")
	public List<Avaliacao> consultarAvaliacoes() {
		return IteratorUtils.toList(repositorio.findAll().iterator());
	}

	public List<Avaliacao> consultarAvaliacoes(String bairro)
			throws MensagemException {

		Endereco endereco = enderecoDominio.consultarEndereco(bairro);
		List<Avaliacao> list = repositorio.findByEndereco(endereco);

		return list;
	}

	public List<Avaliacao> consultarAvaliacoesPorUsuario(Usuario usuario)
			throws MensagemException {

		return repositorio.findByUsuario(usuario);
	}

	public Avaliacao salvarAvaliacao(Avaliacao avaliacao)
			throws MensagemException {

		LOG.debug("Inicio salvarAvaliacao >> " + avaliacao);
		validarAvaliacao(avaliacao);

		try {
			LOG.debug("Salvando avaliacao");
			return repositorio.save(avaliacao);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO, e);
		}
	}

	private void validarAvaliacao(Avaliacao avaliacao) throws MensagemException {

		if (null == avaliacao.getSatisfacao()) {
			LOG.info("Avaliacao sem satisfacao >> " + avaliacao);
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}

		if (null == avaliacao.getArvore()) {
			LOG.info("Avaliacao sem informações da arvore >> " + avaliacao);
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}

		if (!avaliacao.getArvore().isAtivo()) {
			LOG.info("Avaliacao tentando salvar com arvore inativa >> "
					+ avaliacao);
			throw new MensagemException(ErrorMessage.ARVORE_INATIVA);
		}

		Satisfacao satisfacao = satisfacaoDominio.consultarSatisfacao(avaliacao
				.getSatisfacao().getNome());
		avaliacao.setSatisfacao(satisfacao);

		if (null == satisfacao) {
			throw new MensagemException(ErrorMessage.SATISFACAO_INVALIDO);
		}

		if (avaliacao.getDataInclusao() == null) {
			avaliacao.setDataInclusao(new Date());
		}
	}

	public Avaliacao consultarAvaliacao(long id) throws MensagemException {

		if (repositorio.exists(id)) {
			LOG.debug("retornando id >> " + id);
			return repositorio.findOne(id);
		}

		LOG.info("id " + id + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	public List<Avaliacao> consultarAvaliacaoMaiorQue(long data)
			throws MensagemException {

		return repositorio.findByDataInclusaoGreaterThan(new Date(data));
	}

	public void curtirAvaliacao(long id, boolean curtir)
			throws MensagemException {

		Avaliacao avaliacao = consultarAvaliacao(id);

		if (curtir) {
			// avaliacao.getUsuariosCurtiram().add(principal.getName());
			avaliacao.setCurtidas(avaliacao.getCurtidas() + 1);

		} else if (!curtir) {
			// avaliacao.getUsuariosCurtiram().remove(principal.getName());
			avaliacao.setCurtidas(avaliacao.getCurtidas() - 1);

		} else {
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}

		salvarAvaliacao(avaliacao);
	}

	public void deletarAvaliacao(long id) throws MensagemException {

		Avaliacao avaliacao = consultarAvaliacao(id);

		if (avaliacao == null) {
			return;
		}

		repositorio.delete(avaliacao);
	}

}
