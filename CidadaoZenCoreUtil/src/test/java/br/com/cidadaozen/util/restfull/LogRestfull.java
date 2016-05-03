package br.com.cidadaozen.util.restfull;

import java.util.List;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Log.LevelLog;
import br.com.cidadaozen.repositorio.Log;
import br.com.cidadaozen.repositorio.Log.AplicacaoLog;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class LogRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public List<Log> listarTop100Logs() {
		return cidadaoZenApi.consultarLog(null);
	}

	public List<Log> listarLogPorAplicacao(AplicacaoLog aplicacao) {
		return cidadaoZenApi.consultarLog(Util
				.returnMapFiltrosAplicacao(aplicacao.getValue()));
	}

	public List<Log> listarLogPorAplicacaoLevel(AplicacaoLog aplicacao,
			LevelLog level) {
		return cidadaoZenApi.consultarLog(Util.returnMapFiltrosAplicacaoLevel(
				aplicacao.getValue(), level.getValue()));
	}

	public List<Log> listarLogPorAplicacaoLevelTag(AplicacaoLog aplicacao,
			LevelLog level, String tag) {
		return cidadaoZenApi.consultarLog(Util
				.returnMapFiltrosAplicacaoLevelTag(aplicacao.getValue(),
						level.getValue(), tag));
	}

	public void salvarLog(Log log) {
		cidadaoZenApi.salvarLog(log);
	}

	public ErrorRecorder getError() {
		return error;
	}

}
