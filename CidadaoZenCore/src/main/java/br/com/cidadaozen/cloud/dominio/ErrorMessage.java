package br.com.cidadaozen.cloud.dominio;

public enum ErrorMessage {

	SENHA_INVALIDA(301, "Senha inv�lida"),

	DADOS_INVALIDOS(401, "Dados inv�lidos"), DADOS_NAO_ENCONTRADO(402,
			"Dados n�o encontrados."), ENDERECO_NULL(403, "Endere�o Nulo."), EMAIL_NAO_ENCONTRADO(
			404, "E-mail n�o encontrado."), ARVORE_INATIVA(403,
			"Arvore Inativa."),

	GENERICO(500, "Erro Gen�rico"), DATABASE(501, "Erro na grava��o de dados"), USUARIO_DUPLICADO(
			502, "Esse usu�rio j� existe"), SATISFACAO_INVALIDO(503,
			"Satisfacao inv�lido"), IO_EXCEPTION(504, "Erro de IO.");

	private final int code;
	private final String description;

	private ErrorMessage(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}
