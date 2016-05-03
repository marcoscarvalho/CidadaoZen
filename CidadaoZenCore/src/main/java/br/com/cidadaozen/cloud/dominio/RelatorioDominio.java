package br.com.cidadaozen.cloud.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Arvore;
import br.com.cidadaozen.cloud.repositorio.Avaliacao;
import br.com.cidadaozen.cloud.repositorio.AvaliacaoRepositorio;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Endereco;
import br.com.cidadaozen.cloud.repositorio.Relatorio;
import br.com.cidadaozen.cloud.repositorio.Satisfacao;

@Service
public class RelatorioDominio {

	@Autowired
	private ArvoreDominio arvoreDominio;

	@Autowired
	private EnderecoDominio enderecoDominio;

	@Autowired
	private AvaliacaoRepositorio avaliacaoRepositorio;

	@Autowired
	private AvaliacaoDominio avaliacaoDominio;

	@Autowired
	private SatisfacaoDominio satisfacaoDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Map<String, Map<String, Integer>> consultarRelatorioFilhosArvoreBairro(
			long idArvore, String bairro) throws MensagemException {

		Map<String, Map<String, Integer>> retorno = new HashMap<String, Map<String, Integer>>();
		Arvore arvore = arvoreDominio.consultarArvore(idArvore);
		LOG.debug("Arvore encontrada >> " + arvore);

		ajustarObjetoRetorno(arvore, bairro, retorno);

		return retorno;
	}

	public Map<String, Map<String, Integer>> consultarRelatorioFilhosArvoreBairro(
			long idArvore, long idEndereco) throws MensagemException {

		Map<String, Map<String, Integer>> retorno = new HashMap<String, Map<String, Integer>>();
		Arvore arvore = arvoreDominio.consultarArvore(idArvore);
		LOG.debug("Arvore encontrada >> " + arvore);

		Endereco endereco = enderecoDominio.consultarEndereco(idEndereco);
		LOG.debug("Endereco encontrado >> " + endereco);
		String bairro = endereco.getBairro();

		ajustarObjetoRetorno(arvore, bairro, retorno);

		return retorno;
	}

	private void ajustarObjetoRetorno(Arvore arvore, String bairro,
			Map<String, Map<String, Integer>> retorno) {

		if (arvore.getFilhos() != null && arvore.getFilhos().size() > 0) {
			for (Arvore arvoreFilha : arvore.getFilhos()) {
				ajustarObjetoRetorno(arvoreFilha, bairro, retorno);
			}

		} else {
			manipulaRelatorio(bairro, arvore, retorno);
		}
	}

	@SuppressWarnings("rawtypes")
	private void manipulaRelatorio(String bairro, Arvore arvore,
			Map<String, Map<String, Integer>> retorno) {

		LOG.debug("Count avaliacoes da arvore e bairro >> " + arvore + " e "
				+ bairro);
		List list = avaliacaoRepositorio
				.countAvaliacoes(arvore.getId(), bairro);

		Map<String, Integer> value = new HashMap<String, Integer>();

		for (Object object : list) {
			Object[] objs = (Object[]) object;
			value.put(objs[4].toString(), Integer.parseInt(objs[0].toString()));
		}

		retorno.put(arvore.getParametro().getNome(), value);
	}

	public List<Relatorio> consultarListRelatorio(String cidade)
			throws MensagemException {

		List<Relatorio> listRelatorio = new ArrayList<Relatorio>();
		List<Endereco> listEnderecos = enderecoDominio
				.consultarEnderecoPorCidade(cidade);

		for (Endereco endereco : listEnderecos) {
			Relatorio relatorio = new Relatorio();

			List<Avaliacao> listAvaliacoes = avaliacaoDominio
					.consultarAvaliacoes(endereco.getBairro());

			if (null == listAvaliacoes || listAvaliacoes.size() == 0)
				continue;

			List<Satisfacao> satisfacoes = new ArrayList<Satisfacao>();

			for (Avaliacao avaliacao : listAvaliacoes)
				satisfacoes.add(avaliacao.getSatisfacao());

			relatorio.setSatisfacao(fazerMedia(satisfacoes));
			relatorio.setTotalAvaliacoes(listAvaliacoes.size());
			ArrayList<Avaliacao> as = new ArrayList<Avaliacao>();
			as.addAll(listAvaliacoes);
			relatorio.setAvaliacoes(as);
			relatorio.setEndereco(endereco);
			listRelatorio.add(relatorio);
		}

		return listRelatorio;
	}

	private Satisfacao fazerMedia(List<Satisfacao> satisfacoes)
			throws MensagemException {
		int contSatisfeito = 0;
		int contInsatisfeito = 0;
		int contNeutro = 0;

		Satisfacao satisfeito = null;
		Satisfacao insatisfeito = null;
		Satisfacao neutro = null;

		for (Satisfacao satisfacao : satisfacoes) {
			if (Constantes.SATISFEITO.equals(satisfacao.getNome())) {
				contSatisfeito++;
				satisfeito = satisfacao;

			} else if (Constantes.INSATISFEITO.equals(satisfacao.getNome())) {
				contInsatisfeito++;
				insatisfeito = satisfacao;

			} else {
				contNeutro++;
				neutro = satisfacao;
			}
		}

		if (contSatisfeito > contInsatisfeito && contSatisfeito > contNeutro) {
			return satisfeito;

		} else if (contInsatisfeito > contSatisfeito
				&& contInsatisfeito > contNeutro) {
			return insatisfeito;

		} else {

			if (contNeutro == 0) {
				return satisfacaoDominio.consultarSatisfacao(Constantes.NEUTRO);
			}

			return neutro;
		}
	}
}
