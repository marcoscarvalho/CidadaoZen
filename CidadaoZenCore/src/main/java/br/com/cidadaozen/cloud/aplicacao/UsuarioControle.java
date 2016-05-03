package br.com.cidadaozen.cloud.aplicacao;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.dominio.RecursoDominio;
import br.com.cidadaozen.cloud.dominio.UsuarioDominio;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Recurso;
import br.com.cidadaozen.cloud.repositorio.Usuario;

@Controller
public class UsuarioControle {

	@Autowired
	private UsuarioDominio usuarioDominio;

	@Autowired
	private RecursoDominio recursoDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.USUARIO_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Usuario> consultarUsuario(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			Usuario usuario = null;
			String id = filtros.get(Constantes.ID_PARAMETER);
			String email = filtros.get(Constantes.EMAIL_PARAMETER);
			String telefone = filtros.get(Constantes.TELEFONE_PARAMETER);

			if (!Constantes.validarNulo(email)) {
				usuario = usuarioDominio.consultarUsuarioPorEmail(email);

			} else if (!Constantes.validarNulo(telefone)) {
				usuario = usuarioDominio.consultarUsuarioPorTelefone(telefone);

			} else {
				usuario = usuarioDominio.consultarUsuario(Constantes
						.validarLong(id));
			}

			List<Usuario> list = new ArrayList<Usuario>();
			list.add(usuario);
			return list;

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.USUARIO_PATH, method = RequestMethod.PUT)
	public Void putUsuario(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			// TODO Deveria usar o principal. Não vou usar porque o
			// SpringSecurity não está implementado
			String id = filtros.get(Constantes.ID_PARAMETER);
			String permanecerLogado = filtros.get(Constantes.PERMANECER_LOGADO);

			usuarioDominio.logarUsuario(Constantes.validarLong(id));

			if (!Constantes.validarNulo(permanecerLogado)) {
				usuarioDominio.permanecerLogado(Constantes.validarLong(id),
						permanecerLogado);
			}

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.USUARIO_PATH, method = RequestMethod.POST)
	public @ResponseBody Usuario salvarUsuario(@RequestBody Usuario usuario,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + usuario);

			return usuarioDominio.salvarUsuario(usuario);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.USUARIO_RECURSO_PATH, method = RequestMethod.POST)
	public @ResponseBody Recurso workRecurso(@RequestBody Recurso recurso,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + recurso);

			return recursoDominio.workRecurso(recurso);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.USUARIO_PATH, method = RequestMethod.DELETE)
	public Void deletarUsuario(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			usuarioDominio.deletarUsuario(Constantes.validarLong(id));

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

}
