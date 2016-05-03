package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.repositorio.Endereco;
import br.com.cidadaozen.util.restfull.EnderecoRestfull;

public class EnderecoTeste {

	// @Test
	// public void testarCriarEndereco() throws Exception {
	// criarEndereco();
	// }

	// public Endereco criarEndereco() throws Exception {
	// return criarEndereco(UUID.randomUUID().toString(), UUID.randomUUID()
	// .toString(), UUID.randomUUID().toString());
	// }

	// public Endereco criarEndereco(String estado, String cidade, String
	// bairro)
	// throws Exception {
	// Endereco endereco = new Endereco(estado, cidade, bairro);
	// Endereco enderecoRecebido = new EnderecoRestfull()
	// .salvarEndereco(endereco);
	// validarEndereco(endereco, enderecoRecebido);
	//
	// Endereco enderecoRecebido2 = new EnderecoRestfull()
	// .consultarEndereco(enderecoRecebido.getId());
	// validarEndereco(enderecoRecebido, enderecoRecebido2);
	//
	// return enderecoRecebido2;
	// }

	public Endereco getEndereco() throws Exception {
		List<Endereco> list = new EnderecoRestfull()
				.consultarEndereco(CidadaoZenApi.CURITIBA);
		return list.get(RandomUtils.nextInt(0, list.size()));
	}

	public List<Endereco> getEnderecos() throws Exception {
		return new EnderecoRestfull().consultarEndereco(CidadaoZenApi.CURITIBA);
	}

	@Test
	public void testarConsultaEnderecos() throws Exception {

		EnderecoRestfull restfull = new EnderecoRestfull();
		validarConsultaPorLatitudeLongitude(restfull
				.consultarEndereco(new Endereco(-25.4337985, -49.2421502)));

		// String logradouro = "Rua Eduardo Aguirre Calabresi";
		// int numero = 161;
		// Endereco endereco1 = new Endereco();
		// endereco1.setLogradouro(logradouro);
		// endereco1.setNumero(numero);
		// List<Endereco> list1 = restfull.consultarEndereco(endereco1);
		// validarConsultaPorLatitudeLongitude(list1);
		// assertEquals(list1.get(0).getNumero(), numero);

		// Endereco endereco2 = new Endereco();
		// endereco2.setLogradouro(logradouro);
		// validarConsultaPorLatitudeLongitude(restfull
		// .consultarEndereco(endereco2));

		validarConsultaPorLatitudeLongitude(restfull
				.consultarEndereco(new Endereco(-25.5208701, -49.2663465)));

		validarConsultaPorLatitudeLongitude(restfull
				.consultarEndereco(new Endereco(-25.479197, -49.232545)));

		validarConsultaPorLatitudeLongitude(restfull
				.consultarEndereco(new Endereco(-25.4799464, -49.233751)));
	}

	private void validarConsultaPorLatitudeLongitude(List<Endereco> list) {
		assertTrue(list != null);
		assertTrue(list.size() > 0);

		for (Endereco endereco : list) {
			assertTrue(endereco.getBairro() != null);
			assertTrue(endereco.getCidade() != null);
			assertTrue(endereco.getEstado() != null);
			assertTrue(endereco.getLatitude() != 0);
			assertTrue(endereco.getLongitude() != 0);
			// assertTrue(endereco.getCep() != null);
		}
	}

	// @Test
	// public void testarEndereco() throws Exception {
	//
	// EnderecoRestfull enderecoRestfull = new EnderecoRestfull();
	//
	// Endereco enderecoRecebido2 = criarEndereco();
	//
	// enderecoRecebido2.setBairro("Hauer");
	// Endereco enderecoRecebido3 = enderecoRestfull
	// .salvarEndereco(enderecoRecebido2);
	// validarEndereco(enderecoRecebido2, enderecoRecebido3);
	//
	// Endereco enderecoRecebido4 = enderecoRestfull
	// .consultarEndereco(enderecoRecebido2.getId());
	// validarEndereco(enderecoRecebido2, enderecoRecebido4);
	//
	// enderecoRestfull.deletarEndereco(enderecoRecebido4.getId());
	//
	// try {
	// enderecoRestfull.consultarEndereco(enderecoRecebido4.getId());
	// fail("Retornou endereco");
	//
	// } catch (Exception e) {
	// assertEquals(402, enderecoRestfull.getError().getError()
	// .getResponse().getStatus());
	// }
	// }

	// private void validarEndereco(Endereco endereco1, Endereco
	// enderecoRecebido) {
	// assertEquals(enderecoRecebido.getBairro(), endereco1.getBairro());
	// assertEquals(enderecoRecebido.getCidade(), endereco1.getCidade());
	// assertTrue(enderecoRecebido.getId() > 0);
	// }

}
