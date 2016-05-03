package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import br.com.cidadaozen.repositorio.Arvore;
import br.com.cidadaozen.repositorio.Parametro;
import br.com.cidadaozen.util.restfull.ArvoreRestfull;
import br.com.cidadaozen.util.restfull.ParametroRestfull;

public class ArvoreTeste {

	@Test
	public void testarCriarArvore() throws Exception {
		Arvore arvore = criarArvore();
		deletarArvore(new ArvoreRestfull().consultarArvorePai(arvore.getId()));
	}

	public void deletarArvore(Arvore arvore) throws Exception {
		for (Arvore filha : arvore.getFilhos()) {
			new ArvoreRestfull().deletarArvore(filha.getId());
		}
		new ArvoreRestfull().deletarArvore(arvore.getId());
	}

	public void deletarArvore(long id) throws Exception {
		new ArvoreRestfull().deletarArvore(id);
	}

	public Arvore criarArvore() throws Exception {
		List<Parametro> parametros = new ArrayList<Parametro>(
				new ParametroRestfull().consultarListaParametros());
		int qtdeParametros = parametros.size();
		assertTrue(parametros.size() > 0);

		Arvore arv1 = new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros))));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		Arvore arv2 = new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));

		Arvore arvoreColetada2 = new ArvoreRestfull().consultarArvore(arv2
				.getId());
		assertTrue(arvoreColetada2.getId() > 0);
		// assertTrue(arvoreColetada2.getFilhos().size() > 0);

		Arvore arvorePai = new ArvoreRestfull()
				.consultarArvorePai(arvoreColetada2.getId());
		assertTrue(arvorePai != null);
		assertTrue(arvorePai.getId() == arv1.getId());

		arvoreColetada2.setArvorePai(arv1);

		return arvoreColetada2;
	}

	public void criarArvoreAplicacao() throws Exception {
		ParametroRestfull p = new ParametroRestfull();
		ArvoreRestfull a = new ArvoreRestfull();

		Arvore arvPessoa = a.salvarArvore(new Arvore(p
				.consultarParametro("Pessoas")));
		a.salvarArvore(new Arvore(p.consultarParametro("Gentileza"), arvPessoa));
		a.salvarArvore(new Arvore(p.consultarParametro("Respeito aos idosos"),
				arvPessoa));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Música alta no transporte coletivo"),
				arvPessoa));
		a.salvarArvore(new Arvore(p.consultarParametro("Vizinhança"), arvPessoa));

		Arvore arvMobilidade = a.salvarArvore(new Arvore(p
				.consultarParametro("Mobilidade")));

		Arvore arvTransito = a.salvarArvore(new Arvore(p
				.consultarParametro("Trânsito"), arvMobilidade));
		a.salvarArvore(new Arvore(p.consultarParametro("Fluxo de trânsito"),
				arvTransito));
		a.salvarArvore(new Arvore(p.consultarParametro("Qualidade das vias"),
				arvTransito));
		a.salvarArvore(new Arvore(p.consultarParametro("Sinalização"),
				arvTransito));
		a.salvarArvore(new Arvore(p.consultarParametro("Fiscalização"),
				arvTransito));

		Arvore arvTransporteColetivo = a.salvarArvore(new Arvore(p
				.consultarParametro("Transporte coletivo"), arvMobilidade));
		a.salvarArvore(new Arvore(p.consultarParametro("Quantidade de ônibus"),
				arvTransporteColetivo));
		a.salvarArvore(new Arvore(p.consultarParametro("Horário de ônibus"),
				arvTransporteColetivo));
		a.salvarArvore(new Arvore(p.consultarParametro("Opção de ônibus"),
				arvTransporteColetivo));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade de passageiros"),
				arvTransporteColetivo));

		Arvore arvCiclismo = a.salvarArvore(new Arvore(p
				.consultarParametro("Ciclismo"), arvMobilidade));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade de ciclovias"), arvCiclismo));
		a.salvarArvore(new Arvore(p.consultarParametro("Estrutura"),
				arvCiclismo));
		a.salvarArvore(new Arvore(
				p.consultarParametro("Incentivo ao ciclismo"), arvCiclismo));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Respeito aos ciclistas"), arvCiclismo));

		Arvore arvCalcadas = a.salvarArvore(new Arvore(p
				.consultarParametro("Calçadas"), arvMobilidade));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Qualidade das calçadas"), arvCalcadas));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade de calçadas"), arvCalcadas));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Guias rebaixadas para cadeirantes"),
				arvCalcadas));

		Arvore arvSaude = a.salvarArvore(new Arvore(p
				.consultarParametro("Saúde")));

		Arvore arvPublica = a.salvarArvore(new Arvore(p
				.consultarParametro("Pública"), arvSaude));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade de hospitais e leitos"),
				arvPublica));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Tempo para realização de exames"),
				arvPublica));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Qualidade e tempo de atendimento"),
				arvPublica));
		a.salvarArvore(new Arvore(p.consultarParametro("Organização"),
				arvPublica));

		Arvore arvPrivada = a.salvarArvore(new Arvore(p
				.consultarParametro("Privada"), arvSaude));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade de hospitais e leitos"),
				arvPrivada));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Tempo para realização de exames"),
				arvPrivada));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Qualidade e tempo de atendimento"),
				arvPrivada));
		a.salvarArvore(new Arvore(p.consultarParametro("Organização"),
				arvPrivada));

		Arvore arvSaneamentoBasico = a.salvarArvore(new Arvore(p
				.consultarParametro("Saneamento Básico"), arvSaude));
		a.salvarArvore(new Arvore(p.consultarParametro("Distribuição de água"),
				arvSaneamentoBasico));
		a.salvarArvore(new Arvore(p.consultarParametro("Tratamento da água"),
				arvSaneamentoBasico));
		a.salvarArvore(new Arvore(p.consultarParametro("Tratamento de esgoto"),
				arvSaneamentoBasico));
		a.salvarArvore(new Arvore(p.consultarParametro("Coleta de lixo"),
				arvSaneamentoBasico));

		Arvore arvSeguranca = a.salvarArvore(new Arvore(p
				.consultarParametro("Segurança")));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Fiscalização ao tráfico de drogas"),
				arvSeguranca));
		a.salvarArvore(new Arvore(p.consultarParametro("Policiamento"),
				arvSeguranca));
		a.salvarArvore(new Arvore(p.consultarParametro("Combate a violência"),
				arvSeguranca));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Segurança no transporte coletivo"),
				arvSeguranca));

		Arvore arvEducacao = a.salvarArvore(new Arvore(p
				.consultarParametro("Educação")));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Quantidade e localização de escolas"),
				arvEducacao));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Qualidade do Ensino Fundamental"),
				arvEducacao));
		a.salvarArvore(new Arvore(p
				.consultarParametro("Qualidade do Ensino Médio"), arvEducacao));
		a.salvarArvore(new Arvore(p.consultarParametro("Vagas escolares"),
				arvEducacao));
		a.salvarArvore(new Arvore(
				p.consultarParametro("Incentivo educacional"), arvEducacao));
	}

	@Test
	public void testarSalvarArvore() throws Exception {
		List<Parametro> parametros = new ArrayList<Parametro>(
				new ParametroRestfull().consultarListaParametros());
		
		ArvoreRestfull arvoreRestfull = new ArvoreRestfull();
		
		int qtdeParametros = parametros.size();
		assertTrue(parametros.size() > 0);

		Arvore arv1 = arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros))));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));

		Arvore arv1_4 = arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));
		arvoreRestfull.salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));

		Collection<Arvore> arvores2 = arvoreRestfull.consultarArvores();
		assertTrue(arvores2.size() > 0);

		Arvore arvoreColetada2 = arvoreRestfull.consultarArvore(arv1
				.getId());
		assertTrue(arvoreColetada2.getId() > 0);

		Collection<Arvore> arvores = arvoreRestfull.consultarArvores();

		assertTrue(arvores.size() > 0);

		arvoreRestfull.deletarArvore(arvoreColetada2.getId());

		try {
			arvoreRestfull.consultarArvore(arvoreColetada2.getId());
			fail("Retornou");

		} catch (Exception e) {
			assertEquals(402, arvoreRestfull.getError().getError()
					.getResponse().getStatus());
		}

	}

	@Test
	public void testarAddArvore() throws Exception {
		List<Parametro> parametros = new ArrayList<Parametro>(
				new ParametroRestfull().consultarListaParametros());
		int qtdeParametros = parametros.size();
		assertTrue(parametros.size() > 0);

		Arvore arv1 = new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros))));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));

		Arvore arv1_4 = new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));
		new ArvoreRestfull().salvarArvore(new Arvore(parametros
				.get(new Random().nextInt(qtdeParametros)), arv1_4));

		Collection<Arvore> arvores2 = new ArvoreRestfull().consultarArvores();
		assertTrue(arvores2.size() > 0);
		
		new ArvoreRestfull().deletarArvore(arv1.getId());
	}

}
