package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import com.google.common.base.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 5299266044389274069L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_USUARIO, sequenceName = Constantes.SEQ_USUARIO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_USUARIO)
	private long id;

	@Column
	@Length(max = 100)
	private String nome;

	@Column(unique = true)
	@Length(max = 100)
	private String email;

	@Length(max = 15)
	@Column(unique = true)
	private String telefone;

	@Length(max = 64)
	@Column
	private String senha;

	@Column
	private boolean ativo = true;

	@Column
	private boolean admin = false;

	@Column
	private boolean informacoes = false;

	@Column
	private boolean permanecerLogado = false;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@ElementCollection
	@CollectionTable(name = "usuario_login", joinColumns = @JoinColumn(name = "id_usuario"))
	@Column(name = "data_login")
	private List<Date> dataLogin = new ArrayList<Date>();

	@Column(nullable = false, insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(nullable = false, insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	public Usuario() {
		dataInclusao = new Date();
		dataAtualizacao = new Date();
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

	public List<Date> getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(List<Date> dataLogin) {
		this.dataLogin = dataLogin;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(email, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario other = (Usuario) obj;

			// Google Guava provides great utilities for equals too!
			return Objects.equal(email, other.email)
					&& Objects.equal(telefone, other.telefone);

		} else {
			return false;
		}
	}

}
