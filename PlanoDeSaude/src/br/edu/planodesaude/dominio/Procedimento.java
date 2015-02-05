package br.edu.planodesaude.dominio;

import java.util.Locale;


public class Procedimento {

	private int codigo;
	private float preco;
	private String nome;
	private String tipo;
	private Especialidade especialidade;

	public Procedimento() {
		this(0, null, null, null);
	}

	public Procedimento(float preco, String nome, String tipo,
			Especialidade especialidade) {
		super();
		this.preco = preco;
		this.nome = nome;
		this.tipo = tipo;
		this.especialidade = especialidade;
	}

	public Procedimento(int codigo, float preco, String nome, String tipo, Especialidade especialidade) {
		super();
		this.codigo = codigo;
		this.preco = preco;
		this.nome = nome;
		this.tipo = tipo;
		this.especialidade = especialidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return this.getNome();
		/*return String
				.format(Locale.US,
						"ID Procedimento: %d\nPreço: %.2f\nNome: %s\nTipo Procedimento: %s\nEspecialidade: %s",
						getCodigo(), getPreco(), getNome(), getTipo(), especialidade.toString());*/
	}
}
