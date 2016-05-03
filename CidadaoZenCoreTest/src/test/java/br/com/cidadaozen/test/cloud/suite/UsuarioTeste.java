package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import br.com.cidadaozen.repositorio.Usuario;
import br.com.cidadaozen.util.restfull.UsuarioRestfull;

public class UsuarioTeste {

	private Usuario usuario = new Usuario(UUID.randomUUID().toString(), UUID
			.randomUUID().toString(), new Date());

	private Usuario usuarioSimples = new Usuario(UUID.randomUUID().toString(),
			UUID.randomUUID().toString(), new Date());

	@Test
	public void testarCriarUsuario() throws Exception {
		criarUsuario();
	}

	public Usuario criarUsuario() throws Exception {
		Usuario usuarioRecebido = new UsuarioRestfull().salvarUsuario(usuario);
		assertEquals(usuarioRecebido.getNome(), usuarioRecebido.getNome());
		assertEquals(usuarioRecebido.getEmail(), usuarioRecebido.getEmail());
		assertTrue(usuarioRecebido.getId() > 0);

		String valor = UUID.randomUUID().toString();
		usuarioRecebido.setEmail(valor);
		Usuario usuarioRecebido2 = new UsuarioRestfull()
				.salvarUsuario(usuarioRecebido);

		assertTrue(usuarioRecebido2.getEmail() != usuario.getEmail());

		return usuarioRecebido2;
	}

	@Test
	public void testarCriarUsuarioSimples() throws Exception {

		UsuarioRestfull restfull = new UsuarioRestfull();

		Usuario usuarioRecebido = restfull.salvarUsuario(usuarioSimples);
		validarUsuario(usuarioSimples, usuarioRecebido);

		Usuario usuarioRecebido2 = restfull.consultarUsuario(usuarioRecebido
				.getId());
		validarUsuario(usuarioRecebido, usuarioRecebido2);

		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);
		restfull.logarUsuario(usuarioRecebido2);

		Usuario usuarioRecebido3 = restfull.consultarUsuario(usuarioRecebido
				.getId());
		validarUsuario(usuarioRecebido, usuarioRecebido3);

		restfull.logarUsuario(usuarioRecebido2, true);
		Usuario usuarioRecebido4 = restfull.consultarUsuario(usuarioRecebido2
				.getId());
		validarUsuario(usuarioRecebido2, usuarioRecebido4);
		assertTrue(usuarioRecebido4.isPermanecerLogado());

		restfull.logarUsuario(usuarioRecebido2, false);
		usuarioRecebido4 = restfull.consultarUsuario(usuarioRecebido2.getId());
		validarUsuario(usuarioRecebido2, usuarioRecebido4);
		Assert.assertFalse(usuarioRecebido4.isPermanecerLogado());

		restfull.deletarUsuario(usuarioRecebido2.getId());

		try {
			restfull.consultarUsuario(usuarioRecebido2.getId());
			fail("Retornou");

		} catch (Exception e) {
			assertEquals(402, restfull.getError().getError().getResponse()
					.getStatus());
		}

	}

	private void validarUsuario(Usuario usuario1, Usuario usuarioRecebido) {
		assertEquals(usuarioRecebido.getNome(), usuario1.getNome());
		assertEquals(usuarioRecebido.getEmail(), usuario1.getEmail());
		assertTrue(usuarioRecebido.getId() > 0);
	}

}
