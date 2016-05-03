package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.repositorio.Arvore;
import br.com.cidadaozen.repositorio.Avaliacao;
import br.com.cidadaozen.repositorio.Endereco;
import br.com.cidadaozen.repositorio.Relatorio;
import br.com.cidadaozen.repositorio.Satisfacao;
import br.com.cidadaozen.repositorio.Usuario;
import br.com.cidadaozen.util.restfull.ArvoreRestfull;
import br.com.cidadaozen.util.restfull.AvaliacaoRestfull;
import br.com.cidadaozen.util.restfull.RelatorioRestfull;
import br.com.cidadaozen.util.restfull.SatisfacaoRestfull;

public class RelatorioTeste {

	private int qtdReal = 120;

	@Test
	public void testeComArvoreReal() throws Exception {

		AvaliacaoTeste avaliacaoTeste = new AvaliacaoTeste();

		List<Endereco> enderecos = new EnderecoTeste().getEnderecos();
		List<Satisfacao> satisfacoes = new SatisfacaoRestfull()
				.consultarSatisfacoes();
		List<Arvore> arvs = new ArvoreRestfull().consultarArvores();

		for (int i = 0; i < qtdReal; i++) {
			Endereco endereco = enderecos.get(RandomUtils.nextInt(0,
					enderecos.size()));
			Usuario usuario = new UsuarioTeste().criarUsuario();

			Satisfacao satisfacao = satisfacoes.get(RandomUtils.nextInt(0,
					satisfacoes.size()));

			Arvore arvore = null;

			Arvore arvTema = arvs.get(RandomUtils.nextInt(0, arvs.size()));
			Arvore arvSubtema = arvTema.getFilhos().get(
					RandomUtils.nextInt(0, arvTema.getFilhos().size()));

			if (arvSubtema.getFilhos() != null
					&& arvSubtema.getFilhos().size() > 0) {
				arvore = arvSubtema.getFilhos().get(
						RandomUtils.nextInt(0, arvSubtema.getFilhos().size()));

			} else {
				arvore = arvSubtema;
			}

			Avaliacao avaliacao = new Avaliacao(usuario, endereco, satisfacao,
					arvore, UUID.randomUUID().toString());

			Avaliacao avaliacaoRecebido = null;
			try {

				avaliacaoRecebido = new AvaliacaoRestfull()
						.salvarAvaliacao(avaliacao);

			} catch (Exception e) {
				e.printStackTrace();
			}

			avaliacaoTeste.validacaoSimples(avaliacaoRecebido);
			getResultado(endereco.getBairro(), arvore);
			getResultado(endereco.getId(), arvore);

			String espaco = "------------------------------------";
			System.out.println(espaco + "\n\ncount" + i + "\n\n" + espaco);

			if (i % 100 == 0) {
				System.out.println("parar");
			}
		}

		Map<String, String> map = new RelatorioRestfull()
				.consultarRelatorioQtdBairro();
		assertTrue(map != null);

		teste2();
		teste3();

	}

	@Test
	public void teste3() throws Exception {
		List<Relatorio> listRelatorio = new RelatorioRestfull()
				.consultarListRelatorio(CidadaoZenApi.CURITIBA);

		assertNotNull(listRelatorio);

		if (listRelatorio.size() == 0)
			return;
		
		assertTrue(listRelatorio.size() > 0);

		for (Relatorio relatorio : listRelatorio) {
			assertNotNull(relatorio.getAvaliacoes());
			assertNotNull(relatorio.getEndereco());
			assertNotNull(relatorio.getSatisfacao());
			assertTrue(relatorio.getTotalAvaliacoes() > 0);
		}

	}

	@Test
	public void teste2() throws Exception {
		List<Endereco> enderecos = new EnderecoTeste().getEnderecos();
		List<Arvore> arvs = new ArvoreRestfull().consultarArvores();

		for (Arvore arvore : arvs) {
			for (Endereco endereco : enderecos) {
				getResultado(endereco.getId(), arvore);
			}
		}

	}

	private Map<String, Map<String, Integer>> getResultado(String bairro,
			Arvore arvore) {
		Map<String, Map<String, Integer>> map = new RelatorioRestfull()
				.consultarRelatorio(arvore.getId(), bairro);

		System.out.println("Arvore: " + arvore.toString() + " | Bairro: "
				+ bairro + "\nMap: " + map.toString());
		assertTrue(map != null);

		return map;
	}

	private Map<String, Map<String, Integer>> getResultado(long idEndereco,
			Arvore arvore) {
		Map<String, Map<String, Integer>> map = new RelatorioRestfull()
				.consultarRelatorio(arvore.getId(), idEndereco);

		System.out.println("Arvore: " + arvore.toString() + " | Bairro: "
				+ idEndereco + "\nMap: " + map.toString());
		assertTrue(map != null);

		return map;
	}

}
