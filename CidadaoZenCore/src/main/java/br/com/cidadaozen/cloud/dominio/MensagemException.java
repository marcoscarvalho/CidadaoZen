package br.com.cidadaozen.cloud.dominio;

public class MensagemException extends Exception {

	private static final long serialVersionUID = 1810981442714684546L;

	public MensagemException(ErrorMessage errorMessage) {
		super(errorMessage.getCode() + ": " + errorMessage.getDescription());
		this.code = errorMessage.getCode();
		this.description = errorMessage.getDescription();
	}

	public MensagemException(ErrorMessage errorMessage, Throwable cause) {
		super(errorMessage.getCode() + ": " + errorMessage.getDescription(),
				cause);
		this.code = errorMessage.getCode();
		this.description = errorMessage.getDescription();
	}

	private int code;
	private String description;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
