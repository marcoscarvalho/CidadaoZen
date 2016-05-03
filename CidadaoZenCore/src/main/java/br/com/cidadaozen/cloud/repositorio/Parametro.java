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
@Table(name = "parametro")
public class Parametro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_PARAMETRO, sequenceName = Constantes.SEQ_PARAMETRO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_PARAMETRO)
	private long id;

	@Length(max = 100)
	@Column(unique = true)
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

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parametro) {
			Parametro other = (Parametro) obj;

			// Google Guava provides great utilities for equals too!
			return Objects.equal(nome, other.nome);

		} else {
			return false;
		}
	}

}
