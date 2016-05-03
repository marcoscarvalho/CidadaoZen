package br.com.cidadaozen.repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.cidadaozen.CidadaoZenApi;

public class Arvore implements Serializable {

	private static final long serialVersionUID = 7268891315595011462L;

	private long id;
	private List<Arvore> filhos = new ArrayList<Arvore>();
	private Arvore arvorePai;
	private Parametro parametro;
	private boolean ativo;

	public Arvore() {
		this.ativo = true;
	}

	public Arvore(Parametro parametro) {
		this();
		this.parametro = parametro;
	}

	public Arvore(Parametro parametro, Arvore arvorePai) {
		this(parametro);
		this.arvorePai = arvorePai;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(CidadaoZenApi.ABRE_CHAVE);
		str.append("id: ");
		str.append(id);

		if (null != parametro) {
			str.append(CidadaoZenApi.SEPARA);
			str.append(parametro.toString());
		}

		if (null != filhos) {
			str.append(CidadaoZenApi.SEPARA);
			str.append("filhos: " + filhos.size());
		}

		str.append(CidadaoZenApi.FECHA_CHAVE);

		return str.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Arvore> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Arvore> filhos) {
		this.filhos = filhos;
	}

	public Arvore getArvorePai() {
		return arvorePai;
	}

	public void setArvorePai(Arvore arvorePai) {
		this.arvorePai = arvorePai;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
