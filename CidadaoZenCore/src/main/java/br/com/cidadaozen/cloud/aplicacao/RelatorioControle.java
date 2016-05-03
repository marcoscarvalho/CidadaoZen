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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cidadaozen.cloud.dominio.EnderecoDominio;
import br.com.cidadaozen.cloud.dominio.ErrorMessage;
import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.dominio.RelatorioDominio;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Relatorio;

@Controller
public class RelatorioControle {

	@Autowired
	private RelatorioDominio dominio;

	@Autowired
	private EnderecoDominio enderecoDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.RELATORIO_PATH, method = RequestMethod.GET)
	public @ResponseBody Map<String, Map<String, Integer>> consultarRelatorio(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			String idEndereco = filtros.get(Constantes.ID_ENDERECO_PARAMETER);
			String bairro = filtros.get(Constantes.BAIRRO_PARAMETER);

			if (!Constantes.validarNulo(id)
					&& !Constantes.validarNulo(idEndereco)) {
				return dominio.consultarRelatorioFilhosArvoreBairro(
						Constantes.validarLong(id),
						Constantes.validarLong(idEndereco));

			} else if (!Constantes.validarNulo(id)
					&& !Constantes.validarNulo(bairro)) {
				return dominio.consultarRelatorioFilhosArvoreBairro(
						Constantes.validarLong(id), bairro);

			} else {
				throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
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

	@RequestMapping(value = Constantes.RELATORIO_COUNT_PATH, method = RequestMethod.GET)
	public @ResponseBody Map<String, String> consultarRelatorioQtdBairro(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			return enderecoDominio.countEnderecosBairro();

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.RELATORIO_LIST_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Relatorio> consultarListRelatorio(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String cidade = filtros.get(Constantes.CIDADE_PARAMETER);

			if (!Constantes.validarNulo(cidade)) {
				return dominio.consultarListRelatorio(cidade);
				
			} else {
				throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
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
