package br.edu.planodesaude.dominio;

import java.sql.Timestamp;

import br.edu.planodesaude.util.Estabelecimento;

public class Consulta extends EventoMedico {

	private EstabelecimentoMedico estabelecimentoMedico;
	private Medico medico;

	public Consulta() {
		this(0, null, null, null, null, null);
	}

	public Consulta(Usuario usuario, Timestamp dt,
			EstabelecimentoMedico estabelecimentoMedico, Medico medico,
			Procedimento procedimento) {
		super(usuario, dt, procedimento);
		this.medico = medico;
		this.estabelecimentoMedico = estabelecimentoMedico;
	}

	public Consulta(int codigo, Usuario usuario, Timestamp dataEHora,
			EstabelecimentoMedico estabelecimentoMedico, Medico medico,
			Procedimento procedimento) {
		super(codigo, usuario, dataEHora, procedimento);
		this.medico = medico;
		this.estabelecimentoMedico = estabelecimentoMedico;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimentoMedico;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public void setEstabelecimento(EstabelecimentoMedico estabelecimentoMedico) {
		this.estabelecimentoMedico = estabelecimentoMedico;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("\nEstabelecimento: %s\nMédico: %s",  getEstabelecimento()
						.getNome(), medico.toString());
	}
}