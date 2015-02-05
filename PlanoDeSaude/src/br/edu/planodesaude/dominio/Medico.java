package br.edu.planodesaude.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Medico {

	private String crm;
	private String nome;
	private boolean credenciado;
	private ArrayList<Especialidade> especialidades;

	public boolean isCredenciado() {
		return credenciado;
	}

	public void setCredenciado(boolean credenciado) {
		this.credenciado = credenciado;
	}

	public Medico() {
		this(null, null, null, false);
	}

	public Medico(String crm, String nome,
			ArrayList<Especialidade> especialidades, boolean credenciado) {
		super();
		this.crm = crm;
		this.nome = nome;
		this.especialidades = especialidades;
		this.credenciado = credenciado;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	public String geraFatura(ArrayList<EventoMedico> eventos) {
		String str = String.format("\t\t\t\t\t   FATURA MÉDICA - VIA DO ATENDENTE\n\n\t\t\t\t\t--- CRM: %s -- Nome: %s ---\n\n",
				this.getCrm(), this.getNome());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		double parcialReceb, totalReceb = 0;
		for (EventoMedico em : eventos) {
			if (em instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) em;
				str += String
						.format("CNPJ: %s -- Procedimento: %s -- Tipo: Cirurgia -- Preço: R$ %.2f -- Realização: %s\n",
								c.getHospital().getCnpj().getCnpj(), c
										.getProcedimento().getNome(), c
										.getProcedimento().getPreco(), sdf
										.format(c.getDataEHora()));
			} else {
				Consulta c = (Consulta) em;
				str += String
						.format("CNPJ: %s -- Procedimento: %s -- Tipo: Consulta -- Preço: R$ %.2f -- Realização: %s\n",
								c.getEstabelecimento().getCnpj().getCnpj(), c
										.getProcedimento().getNome(), c
										.getProcedimento().getPreco(), sdf
										.format(c.getDataEHora()));
			}
			parcialReceb = em.getProcedimento().getPreco() * 0.1;
			totalReceb += parcialReceb;
			str += String.format("\t\t\tVALOR A RECEBER: R$ %.2f\n\n", parcialReceb);
		}
		str += String.format("\n\t\t\t\t\t\t\t\t\tTOTAL A RECEBER: R$ %.2f\n", totalReceb);
		return str;
	}

	@Override
	public String toString() {
		return this.getNome();
		/*
		 * String ret = String.format("CRM: %s\nNome: %s\nCredenciado: " +
		 * isCredenciado() + "\nEspecialidades:\n", getCrm(), getNome());
		 * 
		 * for(Especialidade especialidade : getEspecialidades()){ ret +=
		 * especialidade.toString(); }
		 * 
		 * return ret;
		 */
	}
	
	@Override
	public boolean equals(Object o){
		try{
			Medico med = (Medico) o;
			if(!med.getCrm().equals(this.getCrm()))return false;
		}catch(ClassCastException ccex){
			return false;
		}
		return true;
	}
}
