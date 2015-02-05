package br.edu.planodesaude.util;

public class Endereco {

	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String referencia;
	private String cep;
	private String estado;

	public Endereco() {
		this(null, null, null, null, null, null, null);
	}

	public Endereco(String rua, String numero, String bairro, String cidade,
			String referencia, String cep, String estado) {
		super();
		this.cidade = cidade;
		this.rua = rua;
		this.referencia = referencia;
		this.bairro = bairro;
		this.numero = numero;
		this.estado = estado;
		this.cep = cep;
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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return String.format(
				"Cidade: %s\nRua: %s\nReferência: %s\nBairro: %s\nNúmero: %s\nCEP: %s\nEstado: %s",
				getCidade(), getRua(), getReferencia(), getBairro(),
				getNumero(), getCep(), getEstado());
	}
}
