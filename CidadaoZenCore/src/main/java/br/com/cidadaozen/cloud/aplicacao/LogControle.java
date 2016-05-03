package br.com.cidadaozen.cloud.aplicacao;

import java.io.IOException;
import java.security.Principal;
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

import br.com.cidadaozen.cloud.dominio.LogDominio;
import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Log;

@Controller
public class LogControle {

	@Autowired
	private LogDominio dominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.LOG_PATH, method = RequestMethod.POST)
	public Void salvarLog(@RequestBody Log log, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + log);

			dominio.inserirLog(log);

		} catch (MensagemException e) {
			// Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			// Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.LOG_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Log> listarTop100Logs(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String aplicacao = filtros.get(Constantes.APLICACAO_PARAMETER);
			String level = filtros.get(Constantes.LEVEL_PARAMETER);
			String tag = filtros.get(Constantes.TAG_PARAMETER);

			if (!Constantes.validarNulo(aplicacao)
					&& !Constantes.validarNulo(level)
					&& !Constantes.validarNulo(tag)) {
				return dominio.consultarLogPorAplicacaoLevelTag(
						Integer.parseInt(aplicacao), Integer.parseInt(level),
						tag);

			} else if (!Constantes.validarNulo(aplicacao)
					&& !Constantes.validarNulo(level)) {
				return dominio.consultarLogPorAplicacaoLevel(
						Integer.parseInt(aplicacao), Integer.parseInt(level));

			} else if (!Constantes.validarNulo(aplicacao)) {
				return dominio.consultarLogPorAplicacao(Integer
						.parseInt(aplicacao));

			} else {
				return dominio.consultarLog();
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
}
