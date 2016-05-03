package br.com.cidadaozen.cloud.repositorio;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import br.com.cidadaozen.cloud.dominio.ErrorMessage;
import br.com.cidadaozen.cloud.dominio.MensagemException;

public class Constantes {

	public static final String ARVORE_PATH = "/arvore";
	public static final String AVALIACAO_PATH = "/avaliacao";
	public static final String ENDERECO_PATH = "/endereco";
	public static final String ENDERECO_OBJ_PATH = "/endereco/obj";
	public static final String LOG_PATH = "/log";
	public static final String PARAMETRO_PATH = "/parametro";
	public static final String RELATORIO_PATH = "/relatorio";
	public static final String RELATORIO_COUNT_PATH = "/relatorio/count";
	public static final String RELATORIO_LIST_PATH = "/relatorio/list";
	public static final String SATISFACAO_PATH = "/satisfacao";
	public static final String USUARIO_PATH = "/usuario";
	public static final String USUARIO_RECURSO_PATH = "/usuario/recurso";

	// Parameter
	public static final String CIDADE_PARAMETER = "cidade";
	public static final String BAIRRO_PARAMETER = "bairro";
	public static final String DATA_PARAMETER = "data";
	public static final String ID_PARAMETER = "id";
	public static final String PERMANECER_LOGADO = "permanecerLogado";
	public static final String ID_ENDERECO_PARAMETER = "id_endereco";
	public static final String NOME_PARAMETER = "nome";
	public static final String TAG_PARAMETER = "tag";
	public static final String LEVEL_PARAMETER = "level";
	public static final String APLICACAO_PARAMETER = "aplicacao";
	public static final String EMAIL_PARAMETER = "email";
	public static final String TELEFONE_PARAMETER = "telefone";
	public static final String ACAO_PARAMETER = "ACAO";

	// Acoes a serem realizadas
	public static final String ACAO_CONSULTA_MAIOR_QUE = "consultarMaiorQue";
	public static final String ACAO_CURTIR = "curtir";
	public static final String ACAO_ALL = "all";
	public static final String ACAO_DESCURTIR = "descurtir";
	public static final String ACAO_RETORNAR_PAI = "retornarPai";
	public static final String ACAO_ATIVAR_ARVORE = "ATIVAR_ARVORE";
	public static final String ACAO_DESATIVAR_ARVORE = "DESATIVAR_ARVORE";

	// Constantes
	public static final String OK = "OK";
	public static final String PT = "pt";
	public static final String PARAMETRO = "Parâmetro";
	public static final String TIPO = "Tipo";

	public static final String ABRE_CHAVE = "[";
	public static final String DOIS_PONTOS = ": ";
	public static final String FECHA_CHAVE = "]";
	public static final String SEPARA = " | ";
	public static final String SPLIT = "#";

	public static final String CURITIBA = "Curitiba";

	public static final SimpleDateFormat FORMATO_PADRAO_HORA = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	public static final String LOG_DEBUG = "LOG_DEBUG";
	public static final String LOG_INFO = "LOG_INFO";

	public static final String CACHEABLE_ARVORE = "CACHEABLE_ARVORE";
	public static final String CACHEABLE_ARVORE_ID = "CACHEABLE_ARVORE_ID";

	public static final String CACHEABLE_PARAMETRO = "CACHEABLE_PARAMETRO";
	public static final String CACHEABLE_PARAMETRO_NOME = "CACHEABLE_PARAMETRO_NOME";

	public static final String CACHEABLE_SATISFACAO = "CACHEABLE_SATISFACAO";
	public static final String CACHEABLE_SATISFACAO_NOME = "CACHEABLE_SATISFACAO_NOME";

	// Sequencias
	public static final String SEQ_SATISFACAO = "seq_satisfacao";
	public static final String SEQ_PARAMETRO = "seq_parametro";
	public static final String SEQ_USUARIO = "seq_usuario";
	public static final String SEQ_LOG_APLICACAO = "seq_log_aplicacao";
	public static final String SEQ_ENDERECO = "seq_endereco";
	public static final String SEQ_AVALIACAO = "seq_avaliacao";
	public static final String SEQ_ARVORE = "seq_arvore";

	public static final String NUMERO = "street_number";
	public static final String LOGRADOURO = "route";
	public static final String BAIRRO = "neighborhood";
	public static final String CIDADE = "locality";
	public static final String ESTADO = "administrative_area_level_1";
	public static final String PAIS = "country";
	public static final String CEP = "postal_code";

	public static final String SATISFEITO = "Satisfeito";
	public static final String NEUTRO = "Neutro";
	public static final String INSATISFEITO = "Insatisfeito";

	public static String retornarString(org.jsoup.nodes.Element element) {

		if (null != element && null != element.text()
				&& !element.text().isEmpty()) {
			return element.text();
		}

		return null;
	}

	public static void gerarExcecao(HttpServletResponse response,
			MensagemException e) throws IOException {
		response.sendError(e.getCode(), e.getMessage());
	}

	public static void gerarExcecao(HttpServletResponse response, Exception e)
			throws IOException {
		response.sendError(500, e.getMessage());
	}

	public static void gerarExcecao(HttpServletResponse response, int excecao)
			throws IOException {
		response.sendError(excecao);
	}

	public static Long validarLong(String id) throws MensagemException {
		try {

			if (validarNulo(id)) {
				throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
			}

			return Long.parseLong(id);

		} catch (NumberFormatException e) {
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}
	}

	public static boolean validarNulo(String valor) {
		if (null == valor || valor.isEmpty()) {
			return true;
		}

		return false;

	}

}
