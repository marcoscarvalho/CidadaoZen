package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;
import java.util.List;

public class Recurso implements Serializable {

	private static final long serialVersionUID = -4189398198081645610L;

	public static final String CONSULTAR = "CONSULTAR";
	public static final String SALVAR_USUARIO = "SALVAR_USUARIO";
	public static final String INFO = "INFO";

	private Usuario usuario;
	private Endereco enderecoUsuario;
	private List<Avaliacao> avaliacoesUsuario;

	private List<Satisfacao> satisfacaos;
	private List<Endereco> enderecos;
	private List<Arvore> arvores;
	private List<Relatorio> relatorios;

	private String acao;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(Endereco enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public List<Avaliacao> getAvaliacoesUsuario() {
		return avaliacoesUsuario;
	}

	public void setAvaliacoesUsuario(List<Avaliacao> avaliacoesUsuario) {
		this.avaliacoesUsuario = avaliacoesUsuario;
	}

	public List<Satisfacao> getSatisfacaos() {
		return satisfacaos;
	}

	public void setSatisfacaos(List<Satisfacao> satisfacaos) {
		this.satisfacaos = satisfacaos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Arvore> getArvores() {
		return arvores;
	}

	public void setArvores(List<Arvore> arvores) {
		this.arvores = arvores;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

}
