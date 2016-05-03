package br.com.cidadaozen.repositorio;

import java.io.Serializable;
import java.util.Date;

import br.com.cidadaozen.CidadaoZenApi;

public class Log implements Serializable {

	private static final long serialVersionUID = -4634972735253990237L;

	private long id;
	private String tag;
	private String mensagem;
	private Date dataInclusao;
	private int level;
	private int aplicacao;

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
		str.append(CidadaoZenApi.FORMATO_PADRAO_HORA.format(dataInclusao));
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
