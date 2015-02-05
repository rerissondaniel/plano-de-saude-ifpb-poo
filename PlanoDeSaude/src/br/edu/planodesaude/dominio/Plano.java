package br.edu.planodesaude.dominio;

import java.util.ArrayList;

public abstract class Plano {

	private int cod;
	private String descricao;

	public Plano(int cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public abstract String geraFatura(Usuario us, ArrayList<EventoMedico> eventos);
	
	@Override
	public String toString(){
		return String.format("Código do Plano: %d\nDescrição do Plano: %s", cod, descricao);
	}
}
