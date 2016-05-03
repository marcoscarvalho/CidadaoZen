package br.com.cidadaozen.repositorio;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 5299266044389274069L;

	private long id;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private Date dataNascimento;
	private boolean ativo;
	private boolean admin;
	private boolean informacoes;
	private boolean permanecerLogado;

	public Usuario() {
		this.ativo = true;
		this.admin = false;
	}

	public Usuario(String nome, String email, Date dataNascimento) {
		this();
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append("nome: ");
		str.append(nome);
		str.append(" - telefone: ");
		str.append(telefone);
		str.append("]");

		return str.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isInformacoes() {
		return informacoes;
	}

	public void setInformacoes(boolean informacoes) {
		this.informacoes = informacoes;
	}

	public boolean isPermanecerLogado() {
		return permanecerLogado;
	}

	public void setPermanecerLogado(boolean permanecerLogado) {
		this.permanecerLogado = permanecerLogado;
	}

}
