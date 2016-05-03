package br.com.cidadaozen.cloud.dominio;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Usuario;
import br.com.cidadaozen.cloud.repositorio.UsuarioRepositorio;

@Service
public class UsuarioDominio {

	@Autowired
	private UsuarioRepositorio repositorio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Usuario salvarUsuario(Usuario usuario) throws MensagemException {

		try {
			LOG.debug("Inicio salvar usuario >> " + usuario);

			usuario.setDataInclusao(new Date());
			usuario.setDataAtualizacao(new Date());

			return repositorio.save(usuario);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public void logarUsuario(long id) throws MensagemException {
		Usuario usuario = consultarUsuario(id);
		usuario.getDataLogin().add(new Date());
		salvarUsuario(usuario);
	}

	public void logarUsuario(Usuario usuario) throws MensagemException {
		usuario.getDataLogin().add(new Date());
		salvarUsuario(usuario);
	}

	public void permanecerLogado(long id, String permanecerLogado)
			throws MensagemException {
		Usuario usuario = consultarUsuario(id);
		usuario.setPermanecerLogado(Boolean.valueOf(permanecerLogado));
		salvarUsuario(usuario);
	}

	public Usuario consultarUsuario(Long id) throws MensagemException {

		if (repositorio.exists(id)) {
			LOG.debug("retornando id >> " + id);
			return repositorio.findOne(id);
		}

		LOG.info("id " + id + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	public Usuario consultarUsuarioPorEmail(String email)
			throws MensagemException {
		LOG.debug("Consultar Usuario por email >> " + email);

		try {
			return repositorio.findByEmail(email);

		} catch (Exception e) {
			throw new MensagemException(ErrorMessage.EMAIL_NAO_ENCONTRADO);
		}
	}

	public Usuario consultarUsuarioPorTelefone(String telefone)
			throws MensagemException {
		LOG.debug("Consultar Usuario por telefone >> " + telefone);
		return repositorio.findByTelefone(telefone);
	}

	public void deletarUsuario(long id) throws MensagemException {

		LOG.debug("Deletando usuario id >> " + id);
		Usuario usuario = consultarUsuario(id);

		if (usuario == null) {
			return;
		}

		LOG.info("Deletando usuario >> " + usuario);
		repositorio.delete(usuario);
	}

}
