package br.com.cidadaozen.repositorio;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 9143344156374015661L;

	private long id;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private double latitude;
	private double longitude;
	private String extra1;
	private String extra2;

	public Endereco() {

	}

	public Endereco(String estado, String cidade, String bairro) {
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
	}

	public Endereco(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Endereco(double latitude, double longitude, String extra1) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.extra1 = extra1;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("[Bairro: ");
		str.append(bairro);
		str.append("]");

		return str.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

}
