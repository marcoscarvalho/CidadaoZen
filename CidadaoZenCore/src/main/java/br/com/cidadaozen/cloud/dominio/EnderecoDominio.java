package br.com.cidadaozen.cloud.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cidadaozen.cloud.repositorio.Endereco;
import br.com.cidadaozen.cloud.repositorio.EnderecoPorLocalizacao;
import br.com.cidadaozen.cloud.repositorio.EnderecoRepositorio;

@Service
public class EnderecoDominio {

	@Autowired
	private EnderecoRepositorio repositorio;

	@Autowired
	private EnderecoPorLocalizacao enderecoPorLocalizacao;

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public Endereco salvarEndereco(Endereco endereco) throws MensagemException {
		try {
			LOG.debug("Salvando endereco >> " + endereco);
			return repositorio.save(endereco);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public Endereco consultarEndereco(Long id) throws MensagemException {

		if (repositorio.exists(id)) {
			LOG.debug("retornando id >> " + id);
			return repositorio.findOne(id);
		}

		LOG.info("id " + id + " não encontrado");
		throw new MensagemException(ErrorMessage.DADOS_NAO_ENCONTRADO);
	}

	public Endereco consultarEndereco(String bairro) throws MensagemException {

		try {
			return repositorio.findByBairro(bairro).get(0);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public List<Endereco> consultarEnderecoPorCidade(String cidade)
			throws MensagemException {

		try {
			return repositorio.findByCidade(cidade);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	@SuppressWarnings("rawtypes")
	public Map<String, String> countEnderecosBairro() throws MensagemException {

		Map<String, String> value = new HashMap<String, String>();
		List list = repositorio.countEnderecosBairro();

		for (Object object : list) {
			Object[] objs = (Object[]) object;
			value.put(objs[0].toString(), objs[1].toString());
		}

		return value;
	}

	public List<Endereco> consultarEnderecoObj(Endereco endereco)
			throws MensagemException {

		try {
			return enderecoPorLocalizacao.consultarEndereco(endereco);

		} catch (MensagemException e) {
			throw e;

		} catch (Exception e) {
			throw new MensagemException(ErrorMessage.GENERICO);
		}
	}

	public void deletarEndereco(long id) throws MensagemException {

		LOG.debug("Deletando endereco >> " + id);
		Endereco endereco = consultarEndereco(id);

		if (endereco == null) {
			return;
		}

		LOG.info("Deletando endereco >> " + endereco);
		repositorio.delete(endereco);
	}

}
