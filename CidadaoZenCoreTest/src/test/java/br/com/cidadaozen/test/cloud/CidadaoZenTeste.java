package br.com.cidadaozen.test.cloud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.cidadaozen.test.cloud.suite.ArvoreTeste;
import br.com.cidadaozen.test.cloud.suite.AvaliacaoTeste;
import br.com.cidadaozen.test.cloud.suite.EnderecoTeste;
import br.com.cidadaozen.test.cloud.suite.LogTeste;
import br.com.cidadaozen.test.cloud.suite.ParametroTeste;
import br.com.cidadaozen.test.cloud.suite.RelatorioTeste;
import br.com.cidadaozen.test.cloud.suite.SatisfacaoTeste;
import br.com.cidadaozen.test.cloud.suite.UsuarioTeste;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AvaliacaoTeste.class, ArvoreTeste.class,
		EnderecoTeste.class, LogTeste.class, ParametroTeste.class,
		UsuarioTeste.class, SatisfacaoTeste.class, RelatorioTeste.class })
public class CidadaoZenTeste {

}