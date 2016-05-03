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

import br.com.cidadaozen.cloud.dominio.EnderecoDominio;
import br.com.cidadaozen.cloud.dominio.ErrorMessage;
import br.com.cidadaozen.cloud.dominio.MensagemException;
import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Endereco;

@Controller
public class EnderecoControle {

	@Autowired
	private EnderecoDominio enderecoDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = Constantes.ENDERECO_PATH, method = RequestMethod.GET)
	public @ResponseBody List<Endereco> consultarEndereco(
			@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		List<Endereco> list = new ArrayList<Endereco>();
		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			String cidade = filtros.get(Constantes.CIDADE_PARAMETER);

			if (!Constantes.validarNulo(id)) {
				list.add(enderecoDominio.consultarEndereco(Constantes
						.validarLong(id)));

			} else if (!Constantes.validarNulo(cidade)) {
				return enderecoDominio.consultarEnderecoPorCidade(cidade);

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

		return list;
	}

	@RequestMapping(value = Constantes.ENDERECO_OBJ_PATH, method = RequestMethod.POST)
	public @ResponseBody List<Endereco> consultarEnderecoObj(
			@RequestBody Endereco endereco, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + endereco);

			return enderecoDominio.consultarEnderecoObj(endereco);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.ENDERECO_PATH, method = RequestMethod.POST)
	public @ResponseBody Endereco salvarEndereco(
			@RequestBody Endereco endereco, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + endereco);

			return enderecoDominio.salvarEndereco(endereco);

		} catch (MensagemException e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);

		} catch (Exception e) {
			Constantes.gerarExcecao(response, e);
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping(value = Constantes.ENDERECO_PATH, method = RequestMethod.DELETE)
	public Void deletarEndereco(@RequestParam Map<String, String> filtros,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		try {
			LOG.info(Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " >> " + filtros);

			String id = filtros.get(Constantes.ID_PARAMETER);
			enderecoDominio.deletarEndereco(Constantes.validarLong(id));

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
