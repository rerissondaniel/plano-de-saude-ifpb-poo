package br.edu.planodesaude.dominio;

public class Especialidade {

	private int codigo;
	private String nome;
	private String descricao;

	public Especialidade() {
		this(0, null, null);
	}

	public Especialidade(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public Especialidade(int codigo, String nome, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {

		return this.getNome();
		/*
		 * return String.format("Código: %d\nNome: %s\nDescrição: %s\n",
		 * getCodigo(), getNome(), getDescricao());
		 */
	}

	@Override
	public boolean equals(Object obj) {
		Especialidade esp = (Especialidade) obj;

		if (this.getCodigo() == esp.getCodigo() && this.getNome().equals(esp.getNome())
				&& this.getDescricao().equals(esp.getDescricao())) {
			return true;
		}
		return false;
	}
}
