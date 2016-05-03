package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import br.com.cidadaozen.repositorio.Arvore;
import br.com.cidadaozen.repositorio.Avaliacao;
import br.com.cidadaozen.repositorio.Endereco;
import br.com.cidadaozen.repositorio.Satisfacao;
import br.com.cidadaozen.repositorio.Usuario;
import br.com.cidadaozen.util.restfull.ArvoreRestfull;
import br.com.cidadaozen.util.restfull.AvaliacaoRestfull;
import br.com.cidadaozen.util.restfull.SatisfacaoRestfull;

public class AvaliacaoTeste {

	@Test
	public void testarCriarAvaliacao() throws Exception {
		Avaliacao avaliacao = criarAvaliacao();
		Arvore arvore = avaliacao.getArvore();
		deletarAvaliacao(avaliacao);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore.getId()));
	}

	public Avaliacao criarAvaliacao() throws Exception {

		Endereco endereco = new EnderecoTeste().getEndereco();
		Arvore arvore = new ArvoreTeste().criarArvore();
		Usuario usuario = new UsuarioTeste().criarUsuario();

		int rand = RandomUtils.nextInt(1, 3);

		Satisfacao satisfacao = null;
		if (rand == 1) {
			satisfacao = new SatisfacaoRestfull()
					.consultarSatisfacao("Satisfeito");

		} else if (rand == 2) {
			satisfacao = new SatisfacaoRestfull().consultarSatisfacao("Neutro");

		} else {
			satisfacao = new SatisfacaoRestfull()
					.consultarSatisfacao("Instatisfeito");
		}

		Avaliacao avaliacao = new Avaliacao(usuario, endereco, satisfacao,
				arvore, UUID.randomUUID().toString());

		Avaliacao avaliacaoRecebido = null;
		try {

			// for (int i = 0; i < RandomUtils.nextInt(1, 10); i++) {
			avaliacaoRecebido = new AvaliacaoRestfull()
					.salvarAvaliacao(avaliacao);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

		validacaoSimples(avaliacaoRecebido);

		return avaliacaoRecebido;
	}

	public void deletarAvaliacao(Avaliacao avaliacao) {
		new AvaliacaoRestfull().deletarAvaliacao(avaliacao.getId());
	}

	@Test
	public void testarConsultarAvaliacao() throws Exception {

		Avaliacao avaliacao = consultarAvaliacao(0);

		Arvore arvore = avaliacao.getArvore();
		deletarAvaliacao(avaliacao);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore.getId()));
	}

	public Avaliacao consultarAvaliacao(long id) throws Exception {

		Avaliacao avaliacao = null;
		Avaliacao avaliacaoRecebido = null;
		if (id == 0) {
			avaliacao = criarAvaliacao();
			avaliacaoRecebido = new AvaliacaoRestfull()
					.consultarAvaliacao(avaliacao.getId());
			validacaoComposta(avaliacao, avaliacaoRecebido);

		} else {
			avaliacaoRecebido = new AvaliacaoRestfull().consultarAvaliacao(id);
			validacaoSimples(avaliacaoRecebido);
		}

		return avaliacaoRecebido;
	}

	@Test
	public void testarCurtirDescurtirAvaliacao() throws Exception {
		Avaliacao avaliacao = criarAvaliacao();
		try {
			new AvaliacaoRestfull().curtirAvaliacao(avaliacao.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		Avaliacao avaliacao2 = consultarAvaliacao(avaliacao.getId());
		assertTrue(avaliacao2.getCurtidas() > avaliacao.getCurtidas());

		new AvaliacaoRestfull().descurtirAvaliacao(avaliacao2.getId());

		Avaliacao avaliacao3 = consultarAvaliacao(avaliacao2.getId());
		assertEquals(avaliacao.getCurtidas(), avaliacao3.getCurtidas());

		Arvore arvore = avaliacao.getArvore();
		deletarAvaliacao(avaliacao);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore.getId()));
	}

	@Test
	public void testarVariasAvaliacoes() throws Exception {
		Avaliacao avaliacao1 = criarAvaliacao();
		Avaliacao avaliacao2 = criarAvaliacao();
		Avaliacao avaliacao3 = criarAvaliacao();
		Avaliacao avaliacao4 = criarAvaliacao();

		Collection<Avaliacao> avaliacoes = new AvaliacaoRestfull()
				.consultarAvaliacoes();
		assertTrue(avaliacoes.size() > 3);

		Arvore arvore1 = avaliacao1.getArvore();
		deletarAvaliacao(avaliacao1);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore1.getId()));

		Arvore arvore2 = avaliacao2.getArvore();
		deletarAvaliacao(avaliacao2);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore2.getId()));

		Arvore arvore3 = avaliacao3.getArvore();
		deletarAvaliacao(avaliacao3);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore3.getId()));

		Arvore arvore4 = avaliacao4.getArvore();
		deletarAvaliacao(avaliacao4);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore4.getId()));

	}

	@Test
	public void testarAvaliacoesMaiorQue() throws Exception {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);

		Endereco endereco = new EnderecoTeste().getEndereco();
		Arvore arvore = new ArvoreTeste().criarArvore();
		Usuario usuario = new UsuarioTeste().criarUsuario();

		List<Satisfacao> satisfacoes = new SatisfacaoRestfull()
				.consultarSatisfacoes();
		Satisfacao satisfacao = satisfacoes.get(RandomUtils.nextInt(0,
				satisfacoes.size()));

		Avaliacao avaliacao = new Avaliacao(usuario, endereco, satisfacao,
				arvore, UUID.randomUUID().toString(), calendar.getTime());
		Avaliacao avaliacaoRecebido = new AvaliacaoRestfull()
				.salvarAvaliacao(avaliacao);

		Avaliacao avaliacaoRecebido2 = new AvaliacaoRestfull()
				.consultarAvaliacao(avaliacaoRecebido.getId());

		assertEquals(avaliacao.getDataInclusao(),
				avaliacaoRecebido2.getDataInclusao());

		Calendar calendarNow = Calendar.getInstance();
		calendarNow.set(Calendar.HOUR, calendarNow.get(Calendar.HOUR) + 1);

		Collection<Avaliacao> avaliacoes = new AvaliacaoRestfull()
				.consultarAvaliacaoMaiorQue(calendarNow.getTimeInMillis());

		for (Avaliacao avaliacao2 : avaliacoes) {
			assertTrue(calendarNow.getTime().compareTo(
					avaliacao2.getDataInclusao()) < 0);
		}

		Arvore arvore2 = avaliacaoRecebido.getArvore();
		deletarAvaliacao(avaliacaoRecebido);
		new ArvoreTeste().deletarArvore(new ArvoreRestfull()
				.consultarArvorePai(arvore2.getId()));
	}

	public void validacaoSimples(Avaliacao avaliacao) {
		assertTrue(avaliacao != null);
		assertTrue(avaliacao.getId() > 0);

		assertTrue(avaliacao.getArvore() != null);
		assertTrue(avaliacao.getArvore().getId() > 0);

		assertTrue(avaliacao.getEndereco() != null);
		assertTrue(avaliacao.getEndereco().getId() > 0);

		assertTrue(avaliacao.getUsuario() != null);
		assertTrue(avaliacao.getUsuario().getId() > 0);

		assertTrue(avaliacao.getObservacao() != null);
		assertTrue(!avaliacao.getObservacao().isEmpty());
	}

	private void validacaoComposta(Avaliacao avaliacao, Avaliacao avaliacao2) {
		validacaoSimples(avaliacao);
		validacaoSimples(avaliacao2);

		assertEquals(avaliacao.getId(), avaliacao2.getId());
		assertEquals(avaliacao.getCurtidas(), avaliacao2.getCurtidas());
		assertEquals(avaliacao.getArvore().getId(), avaliacao2.getArvore()
				.getId());
	}

}
