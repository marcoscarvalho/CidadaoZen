package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

import br.com.cidadaozen.repositorio.Parametro;
import br.com.cidadaozen.util.restfull.ParametroRestfull;

public class ParametroTeste {

	@Test
	public void testarListagemParametro() throws Exception {
		Collection<Parametro> parametros = new ParametroRestfull()
				.consultarListaParametros();
		assertTrue(parametros.size() > 0);
	}

	@Test
	public void testarSalvarParametro() throws Exception {
		Collection<Parametro> parametros = new ParametroRestfull()
				.consultarListaParametros();
		assertTrue(parametros.size() > 0);

		String nome = UUID.randomUUID().toString();
		Parametro parametro = new ParametroRestfull().salvarParametro(new Parametro(nome));

		assertTrue(parametro != null);
		assertTrue(parametro.getId() > 0);
		assertEquals(parametro.getNome(), nome);

		Collection<Parametro> parametrosRecebidos2 = new ParametroRestfull()
				.consultarListaParametros();
		assertTrue(parametrosRecebidos2.size() > parametros.size());
		
		new ParametroRestfull().deletarParametro(parametro.getNome());
		
		Collection<Parametro> parametrosRecebidos3 = new ParametroRestfull()
				.consultarListaParametros();
		assertTrue(parametrosRecebidos3.size() == parametros.size());
	}

}
