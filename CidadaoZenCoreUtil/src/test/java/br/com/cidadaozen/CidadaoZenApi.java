package br.com.cidadaozen;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.QueryMap;
import br.com.cidadaozen.repositorio.Arvore;
import br.com.cidadaozen.repositorio.Avaliacao;
import br.com.cidadaozen.repositorio.Endereco;
import br.com.cidadaozen.repositorio.Log;
import br.com.cidadaozen.repositorio.Parametro;
import br.com.cidadaozen.repositorio.Relatorio;
import br.com.cidadaozen.repositorio.Satisfacao;
import br.com.cidadaozen.repositorio.Usuario;

public interface CidadaoZenApi {

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

	// LOG

	@GET(LOG_PATH)
	public List<Log> consultarLog(@QueryMap Map<String, String> filtros);

	@POST(LOG_PATH)
	public Void salvarLog(@Body Log log);

	// Usuario

	@GET(USUARIO_PATH)
	public List<Usuario> consultarUsuario(@QueryMap Map<String, String> filtros);

	@POST(USUARIO_PATH)
	public Usuario salvarUsuario(@Body Usuario usuario);

	@PUT(USUARIO_PATH)
	public Void putUsuario(@QueryMap Map<String, String> filtros);

	@DELETE(USUARIO_PATH)
	public Void deletarUsuario(@QueryMap Map<String, String> filtros);

	// Endereco

	@GET(ENDERECO_PATH)
	public List<Endereco> consultarEndereco(
			@QueryMap Map<String, String> filtros);

	@POST(ENDERECO_OBJ_PATH)
	public List<Endereco> consultarEnderecoObj(@Body Endereco endereco);

	@POST(ENDERECO_PATH)
	public Endereco salvarEndereco(@Body Endereco endereco);

	// id
	@DELETE(ENDERECO_PATH)
	public Void deletarEndereco(@QueryMap Map<String, String> filtros);

	// Parametro

	@GET(PARAMETRO_PATH)
	public List<Parametro> consultarParametro(
			@QueryMap Map<String, String> filtros);

	@POST(PARAMETRO_PATH)
	public Parametro salvarParametro(@Body Parametro parametro);

	@DELETE(PARAMETRO_PATH)
	public Void deletarParametro(@QueryMap Map<String, String> filtros);

	// Satisfacao

	@GET(SATISFACAO_PATH)
	public List<Satisfacao> consultarSatisfacao(
			@QueryMap Map<String, String> filtros);

	@POST(SATISFACAO_PATH)
	public Satisfacao salvarSatisfacao(@Body Satisfacao satisfacao);

	@DELETE(SATISFACAO_PATH)
	public Void deletarSatisfacao(@QueryMap Map<String, String> filtros);

	// Arvore

	@GET(ARVORE_PATH)
	public List<Arvore> consultarArvore(@QueryMap Map<String, String> filtros);

	@POST(ARVORE_PATH)
	public Arvore salvarArvore(@Body Arvore arvore);

	@PUT(ARVORE_PATH)
	public Void putArvore(@QueryMap Map<String, String> filtros);

	@DELETE(ARVORE_PATH)
	public Void deletarArvore(@QueryMap Map<String, String> filtros);

	// Avaliacao

	@GET(AVALIACAO_PATH)
	public List<Avaliacao> consultarAvaliacao(
			@QueryMap Map<String, String> filtros);

	@POST(AVALIACAO_PATH)
	public Avaliacao salvarAvaliacao(@Body Avaliacao avaliacao);

	@PUT(AVALIACAO_PATH)
	public Void putAvaliacao(@QueryMap Map<String, String> filtros);

	@DELETE(AVALIACAO_PATH)
	public Void deletarAvaliacao(@QueryMap Map<String, String> filtros);

	// Relatório

	@GET(RELATORIO_PATH)
	public Map<String, Map<String, Integer>> consultarRelatorio(
			@QueryMap Map<String, String> filtros);

	@GET(RELATORIO_COUNT_PATH)
	public Map<String, String> consultarRelatorioQtdBairro(
			@QueryMap Map<String, String> filtros);

	@GET(RELATORIO_LIST_PATH)
	public List<Relatorio> consultarListRelatorio(
			@QueryMap Map<String, String> filtros);

}
