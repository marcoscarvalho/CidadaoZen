package br.com.cidadaozen.repositorio;

import java.io.Serializable;

public class Satisfacao implements Serializable {

	private static final long serialVersionUID = 917338754234372104L;

	private long id;
	private String nome;
	private String cor;

	public Satisfacao() {

	}

	public Satisfacao(String nome, String cor) {
		this.nome = nome;
		this.cor = cor;
	}

	@Override
	public String toString() {
		return nome;
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}
