package br.com.cidadaozen.util.restfull;

import java.util.List;
import java.util.Map;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Avaliacao;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class AvaliacaoRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public List<Avaliacao> consultarAvaliacoes() {
		return cidadaoZenApi.consultarAvaliacao(null);
	}

	public Avaliacao consultarAvaliacao(long id) throws Exception {
		List<Avaliacao> list = cidadaoZenApi.consultarAvaliacao(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public List<Avaliacao> consultarAvaliacaoMaiorQue(long data) {
		Map<String, String> map = Util
				.returnMapFiltrosAcao(CidadaoZenApi.ACAO_CONSULTA_MAIOR_QUE);
		map.put(CidadaoZenApi.DATA_PARAMETER, String.valueOf(data));

		return cidadaoZenApi.consultarAvaliacao(map);
	}

	public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
		return cidadaoZenApi.salvarAvaliacao(avaliacao);
	}

	public Void curtirAvaliacao(long id) {
		Map<String, String> map = Util.returnMapFiltros(id);
		map.put(CidadaoZenApi.ACAO_PARAMETER, CidadaoZenApi.ACAO_CURTIR);
		return cidadaoZenApi.putAvaliacao(map);
	}

	public Void descurtirAvaliacao(long id) {
		Map<String, String> map = Util.returnMapFiltros(id);
		map.put(CidadaoZenApi.ACAO_PARAMETER, CidadaoZenApi.ACAO_DESCURTIR);
		return cidadaoZenApi.putAvaliacao(map);
	}

	public Void deletarAvaliacao(long id) {
		return cidadaoZenApi.deletarAvaliacao(Util.returnMapFiltros(id));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
