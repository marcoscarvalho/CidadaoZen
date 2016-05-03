package br.com.cidadaozen.util.restfull;

import java.util.List;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Satisfacao;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class SatisfacaoRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public List<Satisfacao> consultarListaSatisfacao() {
		return cidadaoZenApi.consultarSatisfacao(null);
	}

	public Satisfacao consultarSatisfacao(long id) throws Exception {
		List<Satisfacao> list = cidadaoZenApi.consultarSatisfacao(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public List<Satisfacao> consultarSatisfacoes() throws Exception {
		return cidadaoZenApi.consultarSatisfacao(null);
	}

	public Satisfacao consultarSatisfacao(String nome) throws Exception {
		List<Satisfacao> list = cidadaoZenApi.consultarSatisfacao(Util
				.returnMapFiltros(nome));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Satisfacao salvarSatisfacao(Satisfacao satisfacao) {
		return cidadaoZenApi.salvarSatisfacao(satisfacao);
	}

	public void deletarSatisfacao(long id) {
		cidadaoZenApi.deletarSatisfacao(Util.returnMapFiltros(id));
	}

	public void deletarSatisfacao(String nome) {
		cidadaoZenApi.deletarSatisfacao(Util.returnMapFiltros(nome));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
