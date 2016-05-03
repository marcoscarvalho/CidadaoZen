package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.Objects;

@Entity
@Table(name = "arvore")
public class Arvore implements Serializable {

	private static final long serialVersionUID = -4362812459764363825L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_ARVORE, sequenceName = Constantes.SEQ_ARVORE)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_ARVORE)
	private long id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_arvore")
	private List<Arvore> filhos = new ArrayList<Arvore>();

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_arvore")
	private Arvore arvorePai;

	@ManyToOne
	@JoinColumn(name = "id_parametro", nullable = false)
	private Parametro parametro;

	@Column
	private boolean ativo;

	@Column(nullable = false, insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(nullable = false, insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	public Arvore() {
		this.ativo = true;
		dataInclusao = new Date();
		dataAtualizacao = new Date();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(Constantes.ABRE_CHAVE);
		str.append("id: ");
		str.append(id);

		if (null != parametro) {
			str.append(Constantes.SEPARA);
			str.append(parametro.toString());
		}

		if (null != filhos) {
			str.append(Constantes.SEPARA);
			str.append("filhos: " + filhos.size());
		}

		str.append(Constantes.FECHA_CHAVE);

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

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public Arvore getArvorePai() {
		return arvorePai;
	}

	public void setArvorePai(Arvore arvorePai) {
		this.arvorePai = arvorePai;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(id, parametro);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Arvore) {
			Arvore other = (Arvore) obj;

			// Google Guava provides great utilities for equals too!
			return Objects.equal(id, other.id)
					&& Objects.equal(parametro, other.parametro);

		} else {
			return false;
		}
	}

}
