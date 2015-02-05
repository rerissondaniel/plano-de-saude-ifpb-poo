package br.edu.planodesaude.util;

public abstract class Estabelecimento {

	private String nome;
	private Cnpj cnpj;
	private Endereco endereco;
	
	public Estabelecimento() {
		this(null, null, null);
	}

	public Estabelecimento(Cnpj cnpj, String nome, Endereco endereco) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}

	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
