package br.com.cidadaozen.util.restfull;

import java.util.List;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Endereco;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class EnderecoRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public Endereco consultarEndereco(long id) throws Exception {
		List<Endereco> list = cidadaoZenApi.consultarEndereco(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public List<Endereco> consultarEndereco(String cidade) throws Exception {
		return cidadaoZenApi.consultarEndereco(Util
				.returnMapFiltrosCidade(cidade));
	}

	public List<Endereco> consultarEndereco(Endereco endereco) throws Exception {
		return cidadaoZenApi.consultarEnderecoObj(endereco);
	}

	// public Endereco salvarEndereco(Endereco endereco) {
	// return cidadaoZenApi.salvarEndereco(endereco);
	// }

	public void deletarEndereco(long id) {
		cidadaoZenApi.deletarEndereco(Util.returnMapFiltros(id));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
