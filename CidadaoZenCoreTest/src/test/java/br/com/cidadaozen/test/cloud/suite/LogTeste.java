package br.com.cidadaozen.test.cloud.suite;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import br.com.cidadaozen.repositorio.Log;
import br.com.cidadaozen.repositorio.Log.AplicacaoLog;
import br.com.cidadaozen.repositorio.Log.LevelLog;
import br.com.cidadaozen.util.restfull.LogRestfull;

public class LogTeste {

	private String quebrar(String str, int tamanho) {
		return str.substring(0, tamanho);
	}

	@Test
	public void testarInsercaoDeLogs() throws Exception {
		LogRestfull logNuvem = new LogRestfull();
		String tag1 = quebrar(UUID.randomUUID().toString(), 30);
		String tag2 = quebrar(UUID.randomUUID().toString(), 30);
		String tag3 = quebrar(UUID.randomUUID().toString(), 30);

		int valor = 120;
		for (int i = 0; i < valor; i++) {
			logNuvem.salvarLog(new Log(tag1, getMsg(), LevelLog.INFO,
					AplicacaoLog.CIDADAO_ZEN_ANDROID));

			logNuvem.salvarLog(new Log(tag2, getMsg(), LevelLog.DEBUG,
					AplicacaoLog.CIDADAO_ZEN_CORE));

			logNuvem.salvarLog(new Log(tag3, getMsg(), LevelLog.ERROR,
					AplicacaoLog.CIDADAO_ZEN_WEB));
		}

		List<Log> lista = logNuvem.listarTop100Logs();
		assertTrue(lista.size() <= 100);

		List<Log> lista1 = logNuvem
				.listarLogPorAplicacao(AplicacaoLog.CIDADAO_ZEN_ANDROID);

		List<Log> lista2 = logNuvem.listarLogPorAplicacaoLevel(
				AplicacaoLog.CIDADAO_ZEN_ANDROID, LevelLog.INFO);

		List<Log> lista4 = logNuvem.listarLogPorAplicacaoLevelTag(
				AplicacaoLog.CIDADAO_ZEN_ANDROID, LevelLog.INFO, tag1);

		List<Log> lista3 = logNuvem.listarTop100Logs();

		assertTrue(lista1.size() > 0);
		assertTrue(lista2.size() > 0);
		assertTrue(lista3.size() > 0);
		assertTrue(lista4.size() > 0);
	}

	private String getMsg() {
		StringBuilder str = new StringBuilder("");
		int tamanho = 1000;

		while (str.toString().length() < tamanho) {
			str.append(UUID.randomUUID().toString());
		}

		return str.toString();
	}
}
