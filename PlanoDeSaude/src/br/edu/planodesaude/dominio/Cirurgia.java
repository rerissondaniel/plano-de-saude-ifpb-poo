package br.edu.planodesaude.dominio;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Cirurgia extends EventoMedico {

	private Hospital hospital;
	private ArrayList<Medico> medicos;

	public Cirurgia() {
		this(0, null, null, null, null, null);
	}

	public Cirurgia(Usuario usuario, Timestamp dt, Hospital hospital,
			ArrayList<Medico> medicos, Procedimento procedimento) {
		super(usuario, dt, procedimento);
		this.medicos = medicos;
		this.hospital = hospital;
	}

	public Cirurgia(int codigo, Usuario usuario, Timestamp dt,
			Hospital hospital, ArrayList<Medico> medicos,
			Procedimento procedimento) {
		super(codigo, usuario, dt, procedimento);
		this.medicos = medicos;
		this.hospital = hospital;
	}

	public ArrayList<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(ArrayList<Medico> medicos) {
		this.medicos = medicos;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		String ret = super.toString()
				+ String.format("\nEstabelecimento: %s\n", getHospital()
						.getNome()) + "Medico(s):\n";
		for (Medico medico : getMedicos()) {
			ret += medico.toString();
		}
		return ret;
	}
}