package br.com.cidadaozen.util.restfull;

import java.util.List;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Parametro;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class ParametroRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public List<Parametro> consultarListaParametros() {
		return cidadaoZenApi.consultarParametro(null);
	}

	public Parametro consultarParametro(long id) throws Exception {
		List<Parametro> list = cidadaoZenApi.consultarParametro(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Parametro consultarParametro(String nome) throws Exception {
		List<Parametro> list = cidadaoZenApi.consultarParametro(Util
				.returnMapFiltros(nome));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Parametro salvarParametro(Parametro parametro) {
		return cidadaoZenApi.salvarParametro(parametro);
	}

	public void deletarParametro(long id) {
		cidadaoZenApi.deletarParametro(Util.returnMapFiltros(id));
	}

	public void deletarParametro(String nome) {
		cidadaoZenApi.deletarParametro(Util.returnMapFiltros(nome));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
