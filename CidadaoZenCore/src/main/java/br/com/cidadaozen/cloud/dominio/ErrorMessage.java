package br.com.cidadaozen.cloud.dominio;

public enum ErrorMessage {

	SENHA_INVALIDA(301, "Senha inválida"),

	DADOS_INVALIDOS(401, "Dados inválidos"), DADOS_NAO_ENCONTRADO(402,
			"Dados não encontrados."), ENDERECO_NULL(403, "Endereço Nulo."), EMAIL_NAO_ENCONTRADO(
			404, "E-mail não encontrado."), ARVORE_INATIVA(403,
			"Arvore Inativa."),

	GENERICO(500, "Erro Genérico"), DATABASE(501, "Erro na gravação de dados"), USUARIO_DUPLICADO(
			502, "Esse usuário já existe"), SATISFACAO_INVALIDO(503,
			"Satisfacao inválido"), IO_EXCEPTION(504, "Erro de IO.");

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
