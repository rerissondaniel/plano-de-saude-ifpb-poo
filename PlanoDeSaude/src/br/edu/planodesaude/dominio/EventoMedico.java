package br.edu.planodesaude.dominio;

import java.sql.Timestamp;

public abstract class EventoMedico {

	private int codigo;

	private Usuario usuario;
	private Timestamp dataEHora;
	private Procedimento procedimento;
	
	public EventoMedico() {
		this(null, null, null);
	}

	public EventoMedico(int codigo, Usuario usuario, Timestamp dataEHora,
			Procedimento procedimento) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.dataEHora = dataEHora;
		this.procedimento = procedimento;
	}

	public EventoMedico(Usuario usuario, Timestamp dataEHora, Procedimento procedimento) {
		super();
		this.usuario = usuario;
		this.dataEHora = dataEHora;
		this.procedimento = procedimento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getDataEHora() {
		return dataEHora;
	}

	public void setDataEHora(Timestamp dataEHora) {
		this.dataEHora = dataEHora;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	@Override
	public String toString() {
		String ret = String.format("Código Procedimento Realizado: %d\nUsuário: %s\nData e Hora: %s\nProcedimento: %s ", getCodigo(),
				getUsuario().toString(), dataEHora.toString(), procedimento.toString());
		return ret;
	}
}