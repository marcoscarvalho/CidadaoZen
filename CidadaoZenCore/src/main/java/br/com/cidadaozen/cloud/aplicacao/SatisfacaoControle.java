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
import br.com.cidadaozen.cloud.dominio.SatisfacaoDominio;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Satisfacao;

@Controller
public class SatisfacaoControle {

	@Autowired
	private SatisfacaoDominio dominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.SATISFACAO_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Satisfacao> consultarSatisfacao(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			if (filtros.size() == 0) {
				return dominio.consultarListaSatisfacao();
			}

			String nome = filtros.get(Constantes.NOME_PARAMETER);
			List<Satisfacao> list = new ArrayList<Satisfacao>();
			list.add(dominio.consultarSatisfacao(nome));
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

	@RequestMapping(value = Constantes.SATISFACAO_PATH, method = RequestMethod.POST)
	public @ResponseBody Satisfacao salvarSatisfacao(
			@RequestBody Satisfacao satisfacao, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + satisfacao);
			return dominio.salvarSatisfacao(satisfacao);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.SATISFACAO_PATH, method = RequestMethod.DELETE)
	public Void deletarSatisfacao(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String nome = filtros.get(Constantes.NOME_PARAMETER);
			dominio.deletarSatisfacao(nome);

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
