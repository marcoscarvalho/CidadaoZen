package br.com.cidadaozen.util.restfull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cidadaozen.CidadaoZenApi;
import br.com.cidadaozen.CidadaoZenConexao;
import br.com.cidadaozen.repositorio.Usuario;
import br.com.cidadaozen.util.ErrorRecorder;
import br.com.cidadaozen.util.Util;

public class UsuarioRestfull {

	private ErrorRecorder error = CidadaoZenConexao.getErrorRecorder();
	private CidadaoZenApi cidadaoZenApi = CidadaoZenConexao
			.getCidadaoZenApi(error);

	public Usuario consultarUsuario(long id) throws Exception {
		List<Usuario> list = cidadaoZenApi.consultarUsuario(Util
				.returnMapFiltros(id));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public Usuario consultarUsuario(String email) throws Exception {
		List<Usuario> list = cidadaoZenApi.consultarUsuario(Util
				.returnMapFiltrosEmail(email));

		if (list.size() > 1) {
			throw new Exception();
		}

		return list.get(0);
	}

	public void logarUsuario(Usuario usuario) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.ID_PARAMETER, String.valueOf(usuario.getId()));
		cidadaoZenApi.putUsuario(filtros);
	}

	public void logarUsuario(Usuario usuario, boolean permanecerLogado) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(CidadaoZenApi.ID_PARAMETER, String.valueOf(usuario.getId()));
		filtros.put(CidadaoZenApi.PERMANECER_LOGADO,
				String.valueOf(permanecerLogado));
		cidadaoZenApi.putUsuario(filtros);
	}

	public Usuario salvarUsuario(Usuario usuario) {
		return cidadaoZenApi.salvarUsuario(usuario);
	}

	public Void deletarUsuario(long id) {
		return cidadaoZenApi.deletarUsuario(Util.returnMapFiltros(id));
	}

	public ErrorRecorder getError() {
		return error;
	}

}
