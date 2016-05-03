package br.com.cidadaozen.cloud.dominio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Constantes;
import br.com.cidadaozen.cloud.repositorio.Recurso;
import br.com.cidadaozen.cloud.repositorio.Usuario;

@Service
public class RecursoDominio {

	@Autowired
	private UsuarioDominio usuarioDominio;

	@Autowired
	private AvaliacaoDominio avaliacaoDominio;

	@Autowired
	private RelatorioDominio relatorioDominio;

	@Autowired
	private ArvoreDominio arvoreDominio;

	@Autowired
	private EnderecoDominio enderecoDominio;

	@Autowired
	private SatisfacaoDominio satisfacaoDominio;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Recurso workRecurso(Recurso recurso) throws MensagemException {

		try {
			Usuario u = validarUsuario(recurso.getUsuario());
			return validarEnderecoUsuario(u);

		} catch (MensagemException e) {
			throw e;

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	private Usuario validarUsuario(Usuario u) throws MensagemException {
		if (null == u || Constantes.validarNulo(u.getEmail())
				|| Constantes.validarNulo(u.getSenha()))
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);

		Usuario u2 = usuarioDominio.consultarUsuarioPorEmail(u.getEmail());

		if (!u2.getSenha().equals(u.getSenha()))
			throw new MensagemException(ErrorMessage.SENHA_INVALIDA);

		usuarioDominio.logarUsuario(u2);

		return u2;
	}

	private Recurso validarEnderecoUsuario(Usuario usuario)
			throws MensagemException {

		String cidade = Constantes.CURITIBA;
		Recurso r = new Recurso();
		r.setUsuario(usuario);

		if (r.getEnderecoUsuario() != null
				&& !Constantes.validarNulo(r.getEnderecoUsuario().getCidade()))
			cidade = r.getEnderecoUsuario().getCidade();

		r.setArvores(arvoreDominio.consultarArvores());

		r.setAvaliacoesUsuario(avaliacaoDominio.consultarAvaliacoesPorUsuario(r
				.getUsuario()));

		r.setEnderecos(enderecoDominio.consultarEnderecoPorCidade(cidade));

		r.setRelatorios(relatorioDominio.consultarListRelatorio(cidade));

		r.setSatisfacaos(satisfacaoDominio.consultarListaSatisfacao());

		return r;
	}
}
