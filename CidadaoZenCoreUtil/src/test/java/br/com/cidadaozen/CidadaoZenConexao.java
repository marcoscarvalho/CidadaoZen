package br.com.cidadaozen;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;
import br.com.cidadaozen.util.DateTypeAdapter;
import br.com.cidadaozen.util.ErrorRecorder;

import com.google.gson.GsonBuilder;

public class CidadaoZenConexao {

	private static String SERVER = "http://localhost:8080";

	// private static String SERVER = "http://core.cidadaozen.com.br";

	public static CidadaoZenApi getCidadaoZenApi(String server,
			ErrorRecorder error) {
		SERVER = server;
		return getCidadaoZenApi(error);
	}

	public static CidadaoZenApi getCidadaoZenApi(ErrorRecorder error) {

		return new RestAdapter.Builder()
				.setEndpoint(SERVER)
				.setLogLevel(LogLevel.FULL)
				.setConverter(
						new GsonConverter(new GsonBuilder()
								.registerTypeAdapter(Date.class,
										new DateTypeAdapter()).create()))
				.setErrorHandler(error).build().create(CidadaoZenApi.class);
	}

	public static ErrorRecorder getErrorRecorder() {
		return new ErrorRecorder();
	}

}
