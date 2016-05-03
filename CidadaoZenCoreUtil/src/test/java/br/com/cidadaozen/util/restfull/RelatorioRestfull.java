package br.com.cidadaozen.util.restfull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Relatorio;
import br.com.cidadaozen.util.ErrorRecorder;

public class RelatorioRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public Map<String, Map<String, Integer>> consultarRelatorio(long idArvore,
			String bairro) {

		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.BAIRRO_PARAMETER, bairro);
		filtros.put(CidadaoZenApi.ID_PARAMETER, String.valueOf(idArvore));
		return cidadaoZenApi.consultarRelatorio(filtros);

	}

	public List<Relatorio> consultarListRelatorio(String cidade) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.CIDADE_PARAMETER, cidade);
		return cidadaoZenApi.consultarListRelatorio(filtros);
	}

	public Map<String, Map<String, Integer>> consultarRelatorio(long idArvore,
			long idBairro) {

		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.ID_ENDERECO_PARAMETER,
				String.valueOf(idBairro));
		filtros.put(CidadaoZenApi.ID_PARAMETER, String.valueOf(idArvore));
		return cidadaoZenApi.consultarRelatorio(filtros);

	}

	public Map<String, String> consultarRelatorioQtdBairro() {
		return cidadaoZenApi.consultarRelatorioQtdBairro(null);

	}

	public ErrorRecorder getError() {
		return error;
	}

}