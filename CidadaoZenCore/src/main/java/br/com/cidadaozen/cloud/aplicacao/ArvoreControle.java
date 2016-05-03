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

import br.com.cidadaozen.cloud.dominio.ArvoreDominio;
import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.repositorio.Arvore;
import br.com.cidadaozen.cloud.repositorio.Constantes;

@Controller
public class ArvoreControle {

	@Autowired
	private ArvoreDominio dominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.ARVORE_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Arvore> consultarArvore(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " >> " + filtros);

		try {

			if (filtros.size() == 0) {
				return dominio.consultarArvores();
			}

			String id = filtros.get(Constantes.ID_PARAMETER);
			String acao = filtros.get(Constantes.ACAO_PARAMETER);

			List<Arvore> list = new ArrayList<Arvore>();

			if (acao != null && !acao.isEmpty()
					&& acao.equals(Constantes.ACAO_RETORNAR_PAI)) {
				list.add(dominio.consultarArvorePai(Constantes.validarLong(id)));

			} else {
				list.add(dominio.consultarArvore(Constantes.validarLong(id)));
			}

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

	@RequestMapping(value = Constantes.ARVORE_PATH, method = RequestMethod.POST)
	public @ResponseBody Arvore salvarArvore(@RequestBody Arvore arvore,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " >> " + arvore);

		try {
			return dominio.salvarArvore(arvore);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.ARVORE_PATH, method = RequestMethod.PUT)
	public Void putArvore(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);
			String id = filtros.get(Constantes.ID_PARAMETER);
			String acao = filtros.get(Constantes.ACAO_PARAMETER);

			if (!Constantes.validarNulo(acao)
					&& acao.equals(Constantes.ACAO_ATIVAR_ARVORE)) {
				dominio.ativarArvore(Constantes.validarLong(id), true);

			} else if (!Constantes.validarNulo(acao)
					&& acao.equals(Constantes.ACAO_DESATIVAR_ARVORE)) {
				dominio.ativarArvore(Constantes.validarLong(id), false);
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

	@RequestMapping(value = Constantes.ARVORE_PATH, method = RequestMethod.DELETE)
	public Void deletarArvore(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);
			String id = filtros.get(Constantes.ID_PARAMETER);
			dominio.deletarArvore(Constantes.validarLong(id));

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
