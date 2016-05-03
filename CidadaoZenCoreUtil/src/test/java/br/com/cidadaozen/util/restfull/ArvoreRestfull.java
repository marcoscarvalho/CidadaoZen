package br.com.cidadaozen.util.restfull;

import java.util.List;
import java.util.Map;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Arvore;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class ArvoreRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public List<Arvore> consultarArvores() {
		return cidadaoZenApi.consultarArvore(null);
	}

	public Arvore consultarArvore(long id) throws Exception {
		List<Arvore> list = cidadaoZenApi.consultarArvore(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Arvore consultarArvorePai(long id) throws Exception {
		Map<String, String> map = Util.returnMapFiltros(id);
		map.put(CidadaoZenApi.ACAO_PARAMETER, CidadaoZenApi.ACAO_RETORNAR_PAI);
		List<Arvore> list = cidadaoZenApi.consultarArvore(map);

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Arvore salvarArvore(Arvore arvore) {
		return cidadaoZenApi.salvarArvore(arvore);
	}

	public void deletarArvore(long id) {
		cidadaoZenApi.deletarArvore(Util.returnMapFiltros(id));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
