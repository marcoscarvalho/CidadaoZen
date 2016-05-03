package br.com.cidadaozen.repositorio;

import java.io.Serializable;

public class Parametro implements Serializable {

	private static final long serialVersionUID = 4383942739648741812L;

	private long id;
	private String nome;

	public Parametro() {

	}

	public Parametro(String nome) {
		this.nome = nome;
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

}
