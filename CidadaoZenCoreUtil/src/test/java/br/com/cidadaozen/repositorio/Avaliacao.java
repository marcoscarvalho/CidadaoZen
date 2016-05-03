package br.com.cidadaozen.repositorio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.cidadaozen.CidadaoZenApi;

public class Avaliacao implements Serializable {

	private static final long serialVersionUID = -3260521647588401407L;

	private long id;
	private long curtidas;
	private String observacao;
	private Usuario usuario;
	private Endereco endereco;
	private Arvore arvore;
	private Satisfacao satisfacao;
	private Set<String> usuariosCurtiram = new HashSet<String>();
	private Date dataInclusao;
	
	public Avaliacao(Endereco endereco, Satisfacao satisfacao, Arvore arvore) {
		this.endereco = endereco;
		this.arvore = arvore;
		this.dataInclusao = new Date();
		this.satisfacao = satisfacao;
	}

	public Avaliacao(Usuario usuario, Endereco endereco, Satisfacao satisfacao, Arvore arvore) {
		this(endereco, satisfacao, arvore);
		this.usuario = usuario;
	}

	public Avaliacao(Usuario usuario, Endereco endereco, Satisfacao satisfacao, Arvore arvore, String observacao) {
		this(usuario, endereco, satisfacao, arvore);
		this.observacao = observacao;
	}

	public Avaliacao(Usuario usuario, Endereco endereco, Satisfacao satisfacao, Arvore arvore, String observacao, Date dataInclusao) {
		this(usuario, endereco, satisfacao, arvore, observacao);
		this.dataInclusao = dataInclusao;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(CidadaoZenApi.ABRE_CHAVE);
		str.append("id: ");
		str.append(id);
		str.append(CidadaoZenApi.SEPARA);
		str.append("curtidas: ");
		str.append(curtidas);
		str.append(CidadaoZenApi.SEPARA);
		str.append(satisfacao);
		str.append(CidadaoZenApi.SEPARA);
		str.append("endereco: ");
		str.append(endereco);
		str.append(CidadaoZenApi.FECHA_CHAVE);

		return str.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(long curtidas) {
		this.curtidas = curtidas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Arvore getArvore() {
		return arvore;
	}

	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Set<String> getUsuariosCurtiram() {
		return usuariosCurtiram;
	}

	public void setUsuariosCurtiram(Set<String> usuariosCurtiram) {
		this.usuariosCurtiram = usuariosCurtiram;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Satisfacao getSatisfacao() {
		return satisfacao;
	}

	public void setSatisfacao(Satisfacao satisfacao) {
		this.satisfacao = satisfacao;
	}

}
