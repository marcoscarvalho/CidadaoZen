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
import br.com.cidadaozen.cloud.dominio.ParametroDominio;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Parametro;

@Controller
public class ParametroControle {

	@Autowired
	private ParametroDominio dominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.PARAMETRO_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Parametro> consultarParametro(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			if (filtros.size() == 0) {
				return dominio.consultarListaParametros();
			}

			String nome = filtros.get(Constantes.NOME_PARAMETER);
			List<Parametro> list = new ArrayList<Parametro>();
			list.add(dominio.consultarParametro(nome));
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

	@RequestMapping(value = Constantes.PARAMETRO_PATH, method = RequestMethod.POST)
	public @ResponseBody Parametro salvarParametro(
			@RequestBody Parametro parametro, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + parametro);

			return dominio.salvarParametro(parametro);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.PARAMETRO_PATH, method = RequestMethod.DELETE)
	public Void deletarParametro(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String nome = filtros.get(Constantes.NOME_PARAMETER);
			dominio.deletarParametro(nome);

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
