package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

import br.com.cidadaozen.repositorio.Satisfacao;
import br.com.cidadaozen.util.Util;
import br.com.cidadaozen.util.restfull.SatisfacaoRestfull;

public class SatisfacaoTeste {

	@Test
	public void testarListagemSatisfacao() throws Exception {
		Collection<Satisfacao> satisfacaos = new SatisfacaoRestfull()
				.consultarListaSatisfacao();
		assertTrue(satisfacaos.size() > 0);
	}

	private Satisfacao criarSatisfacao() {
		String nome = UUID.randomUUID().toString();
		String cor = Util.cut(UUID.randomUUID().toString(), 20);
		Satisfacao satisfacao = new SatisfacaoRestfull().salvarSatisfacao(new Satisfacao(
				nome, cor));

		assertTrue(satisfacao != null);
		assertTrue(satisfacao.getId() > 0);
		assertEquals(satisfacao.getNome(), nome);
		
		return satisfacao;
	}

	@Test
	public void testarSalvarSatisfacao() throws Exception {
		Collection<Satisfacao> satisfacaos = new SatisfacaoRestfull()
				.consultarListaSatisfacao();
		assertTrue(satisfacaos.size() > 0);

		Satisfacao satisfacao = criarSatisfacao();

		Collection<Satisfacao> satisfacaosRecebidos2 = new SatisfacaoRestfull()
				.consultarListaSatisfacao();
		assertTrue(satisfacaosRecebidos2.size() > satisfacaos.size());

		new SatisfacaoRestfull().deletarSatisfacao(satisfacao.getNome());

		Collection<Satisfacao> satisfacaosRecebidos3 = new SatisfacaoRestfull()
				.consultarListaSatisfacao();
		assertTrue(satisfacaosRecebidos3.size() == satisfacaos.size());
	}

}
