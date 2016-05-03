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

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3069821211011467855L;

	@Id
	@Column
	@SequenceGenerator(name = Constantes.SEQ_ENDERECO, sequenceName = Constantes.SEQ_ENDERECO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constantes.SEQ_ENDERECO)
	private long id;

	@Length(max = 100)
	@Column
	private String bairro;

	@Length(max = 100)
	@Column
	private String cidade;

	@Length(max = 100)
	@Column
	private String estado;

	@Length(max = 100)
	@Column
	private String pais;

	@Column
	private double latitude;

	@Column
	private double longitude;

	@Length(max = 20)
	@Column
	private String extra1;

	@Length(max = 20)
	@Column
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
