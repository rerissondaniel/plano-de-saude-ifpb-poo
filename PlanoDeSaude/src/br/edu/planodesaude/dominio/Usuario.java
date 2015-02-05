package br.edu.planodesaude.dominio;

import java.sql.Date;

import br.edu.planodesaude.util.Cpf;
import br.edu.planodesaude.util.Endereco;

public class Usuario {

	private int codigo;
	private String nome;
	private String telefone;
	private Cpf cpf;
	private Endereco endereco;
	private Date dataEntrada;
	private Plano plano;
	private boolean ativo;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario() {
		this(null, null, null, null, false, null, null);
	}
	
	public Usuario(int codigo, Cpf cpf, String nome, String telefone,
			Endereco endereco, boolean ativo, Date dataEntrada, Plano plano) {
		this(cpf, nome, telefone, endereco, ativo, dataEntrada, plano);
		this.codigo = codigo;
	}
	
	public Usuario(Cpf cpf, String nome, String telefone,
			Endereco endereco, boolean ativo, Date dataEntrada, Plano plano) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
		this.dataEntrada = dataEntrada;
		this.plano = plano;
		this.ativo = ativo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Override
	public String toString() {
		return String.format("id: %d\nNome: %s\n%s\nTelefone: %s\nCPF: %s\nData de Assinatura: %s\nPlano: %s",
						getCodigo(), getNome(), getEndereco().toString(),
						getTelefone(), getCpf().toString(), getDataEntrada()
								.toString(), getPlano().toString());
	}
}