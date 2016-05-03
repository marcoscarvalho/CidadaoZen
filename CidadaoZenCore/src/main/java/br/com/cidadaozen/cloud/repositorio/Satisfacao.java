package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.google.common.base.Objects;

@Entity
@Table(name = "satisfacao")
public class Satisfacao implements Serializable {

	private static final long serialVersionUID = -7791888785967026779L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_SATISFACAO, sequenceName = Constantes.SEQ_SATISFACAO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_SATISFACAO)
	private long id;

	@Length(max = 100)
	@Column(unique = true)
	private String nome;

	@Column
	@Length(max = 20)
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

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Satisfacao) {
			Satisfacao other = (Satisfacao) obj;

			// Google Guava provides great utilities for equals too!
			return Objects.equal(nome, other.nome);

		} else {
			return false;
		}
	}

}
