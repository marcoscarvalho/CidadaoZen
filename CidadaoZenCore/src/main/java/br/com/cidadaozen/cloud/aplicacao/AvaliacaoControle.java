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

import br.com.cidadaozen.cloud.dominio.AvaliacaoDominio;
import br.com.cidadaozen.cloud.dominio.ErrorMessage;
import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.repositorio.Avaliacao;
import br.com.cidadaozen.cloud.repositorio.Constantes;

@Controller
public class AvaliacaoControle {

	@Autowired
	private AvaliacaoDominio dominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.AVALIACAO_PATH, method = RequestMethod.POST)
	public @ResponseBody Avaliacao salvarAvaliacao(
			@RequestBody Avaliacao avaliacao, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + avaliacao);

			return dominio.salvarAvaliacao(avaliacao);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.AVALIACAO_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Avaliacao> consultarAvaliacao(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			String data = filtros.get(Constantes.DATA_PARAMETER);

			if (filtros.size() == 0) {
				return dominio.consultarAvaliacoes();

			} else if (!Constantes.validarNulo(id)) {
				List<Avaliacao> list = new ArrayList<Avaliacao>();
				list.add(dominio.consultarAvaliacao(Constantes.validarLong(id)));
				return list;

			} else if (!Constantes.validarNulo(data)) {
				return dominio.consultarAvaliacaoMaiorQue(Constantes
						.validarLong(data));

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

	@RequestMapping(value = Constantes.AVALIACAO_PATH, method = RequestMethod.PUT)
	public Void putAvaliacao(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			String acao = filtros.get(Constantes.ACAO_PARAMETER);

			if (!Constantes.validarNulo(acao)
					&& acao.equals(Constantes.ACAO_CURTIR)) {
				dominio.curtirAvaliacao(Constantes.validarLong(id), true);

			} else if (!Constantes.validarNulo(acao)
					&& acao.equals(Constantes.ACAO_DESCURTIR)) {
				dominio.curtirAvaliacao(Constantes.validarLong(id), false);

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

	@RequestMapping(value = Constantes.AVALIACAO_PATH, method = RequestMethod.DELETE)
	public Void deletarAvaliacao(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			dominio.deletarAvaliacao(Constantes.validarLong(id));

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
