package br.com.cidadaozen.cloud.repositorio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "log_aplicacao")
public class Log implements Serializable {

	private static final long serialVersionUID = -4634972735253990237L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_LOG_APLICACAO, sequenceName = Constantes.SEQ_LOG_APLICACAO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_LOG_APLICACAO)
	private long id;

	@Column
	@Length(max = 30)
	private String tag;

	@Lob
	@Column(columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType")
	private String mensagem;

	// 1 para info
	// 2 para error
	// 3 para debug
	@Column
	private int level;

	// 1 para Core
	// 2 para Android
	// 3 para Nuvem
	@Column
	private int aplicacao;

	@Column(nullable = false, insertable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	public Log() {

	}

	public Log(String tag, String mensagem, LevelLog levelLog,
			AplicacaoLog aplicacaoLog) {
		dataInclusao = new Date();
		this.tag = tag;
		this.mensagem = mensagem;
		this.aplicacao = aplicacaoLog.getValue();
		this.level = levelLog.getValue();
	}

	public enum LevelLog {
		INFO(1), ERROR(2), DEBUG(3);
		private final int value;

		private LevelLog(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum AplicacaoLog {
		CIDADAO_ZEN_ANDROID(1), CIDADAO_ZEN_CORE(2), CIDADAO_ZEN_WEB(3);

		private final int value;

		private AplicacaoLog(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("[");
		str.append(aplicacao);
		str.append(" | ");
		str.append(level);
		str.append(" | ");
		str.append(tag);
		str.append(" | ");
		str.append(Constantes.FORMATO_PADRAO_HORA.format(dataInclusao));
		str.append(" | ");
		str.append(mensagem);
		str.append("]");

		return str.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(int aplicacao) {
		this.aplicacao = aplicacao;
	}

}
