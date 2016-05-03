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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = -3260521647588401407L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_AVALIACAO, sequenceName = Constantes.SEQ_AVALIACAO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_AVALIACAO)
	private long id;

	@Column
	private long curtidas;

	@Length(max = 255)
	@Column
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_arvore")
	private Arvore arvore;

	@ManyToOne
	@JoinColumn(name = "id_satisfacao")
	private Satisfacao satisfacao;

	@ElementCollection
	@CollectionTable(name = "curtidas_avaliacao", joinColumns = @JoinColumn(name = "id_avaliacao"))
	@Column(name = "usuario_avaliacao")
	private List<String> usuariosCurtiram = new ArrayList<String>();

	@Column(nullable = false, insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(Constantes.ABRE_CHAVE);
		str.append("id: ");
		str.append(id);
		str.append(Constantes.SEPARA);
		str.append("curtidas: ");
		str.append(curtidas);
		str.append(Constantes.SEPARA);
		str.append(satisfacao);
		str.append(Constantes.SEPARA);
		str.append("endereco: ");
		str.append(endereco);
		str.append(Constantes.FECHA_CHAVE);

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

	public void setUsuariosCurtiram(List<String> usuariosCurtiram) {
		this.usuariosCurtiram = usuariosCurtiram;
	}

}
