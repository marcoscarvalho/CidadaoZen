package br.com.cidadaozen.util;

import java.util.HashMap;
import java.util.Map;

import br.com.cidadaozen.CidadaoZenApi;

public class Util {

	public static Map<String, String> returnMapFiltros(long id) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.ID_PARAMETER, String.valueOf(id));
		return filtros;
	}

	public static Map<String, String> returnMapFiltros(String nome) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.NOME_PARAMETER, nome);
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosCidade(String cidade) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.CIDADE_PARAMETER, cidade);
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosAplicacao(int aplicacao) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.APLICACAO_PARAMETER,
				String.valueOf(aplicacao));
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosAplicacaoLevel(
			int aplicacao, int level) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.APLICACAO_PARAMETER,
				String.valueOf(aplicacao));
		filtros.put(CidadaoZenApi.LEVEL_PARAMETER, String.valueOf(level));
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosAplicacaoLevelTag(
			int aplicacao, int level, String tag) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.APLICACAO_PARAMETER,
				String.valueOf(aplicacao));
		filtros.put(CidadaoZenApi.LEVEL_PARAMETER, String.valueOf(level));
		filtros.put(CidadaoZenApi.TAG_PARAMETER, tag);
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosEmail(String email) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.EMAIL_PARAMETER, email);
		return filtros;
	}

	public static Map<String, String> returnMapFiltrosAcao(String acao) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.ACAO_PARAMETER, acao);
		return filtros;
	}

	public static String cut(String str, int tamanho) {
		return str.substring(0, tamanho);
	}

}
