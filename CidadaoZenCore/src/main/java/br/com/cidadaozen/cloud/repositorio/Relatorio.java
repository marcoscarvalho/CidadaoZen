package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;
import java.util.List;

public class Relatorio implements Serializable {

	private static final long serialVersionUID = 8051871555329715950L;

	private List<Avaliacao> avaliacoes;
	private Endereco endereco;

	private int totalAvaliacoes;
	private Satisfacao satisfacao;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("[");
		str.append("Relatorio " + endereco.getBairro());
		str.append(" | Satisfação: " + satisfacao.getNome());
		str.append(" | Qtd avaliações: " + totalAvaliacoes);
		str.append("]");
		
		return str.toString();
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getTotalAvaliacoes() {
		return totalAvaliacoes;
	}

	public void setTotalAvaliacoes(int totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public Satisfacao getSatisfacao() {
		return satisfacao;
	}

	public void setSatisfacao(Satisfacao satisfacao) {
		this.satisfacao = satisfacao;
	}

}
