package br.edu.planodesaude.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Endereco;
import br.edu.planodesaude.util.Estabelecimento;

public abstract class EstabelecimentoMedico extends Estabelecimento {

	private ArrayList<Especialidade> especialidades;
	private ArrayList<Medico> medicos;
	private boolean credenciado;
	private final String separa = "----------------------------------------------------------------------------------------------------------------------------------\n";;
	
	public EstabelecimentoMedico() {
		this(null, null, null, null, null, false);
	}

	public EstabelecimentoMedico(Cnpj cnpj, String nome, Endereco endereco,
			ArrayList<Medico> medicos, boolean credenciado) {
		super(cnpj, nome, endereco);
		this.medicos = medicos;
		this.credenciado = credenciado;
	}

	public EstabelecimentoMedico(Cnpj cnpj, String nome, Endereco endereco,
			ArrayList<Medico> medicos, ArrayList<Especialidade> especialidades,
			boolean credenciado) {
		super(cnpj, nome, endereco);
		this.medicos = medicos;
		this.especialidades = especialidades;
		this.credenciado = credenciado;
	}

	public void setCredenciado(boolean credenciado) {
		this.credenciado = credenciado;
	}

	public boolean isCredenciado() {
		return credenciado;
	}

	public ArrayList<Medico> getMedico() {
		return medicos;
	}

	public void setMedico(ArrayList<Medico> medico) {
		this.medicos = medico;
	}

	public ArrayList<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	public String geraFatura(ArrayList<EventoMedico> eventos) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		double receitaParc, receitaTot = 0.0;
		String str = separa;
		str += String.format("\t\tFATURA MÉDICA - VIA DO ESTABELECIMENTO\n" + separa + "\nNome:\t  %s\nCNPJ:\t  %s\n\n", this.getNome(), this.getCnpj().getCnpj());
		for (EventoMedico em : eventos) {
			if (em instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) em;
				str += separa;
				str += String
						.format("CPF: %s\n\nProcedimento: %s\nTipo: Cirurgia\n\nPreço: R$ %.2f\nRealização: %s\n\nMedico(s):\n",
								em.getUsuario().getCpf().getCpf(), em
										.getProcedimento().getNome(), em
										.getProcedimento().getPreco(), sdf
										.format(em.getDataEHora()));
				for (Medico med : c.getMedicos()) {
					str += String.format("   Nome: %s\n   CRM:    %s\n",
							med.getNome(), med.getCrm());
				}
			} else {
				Consulta c = (Consulta) em;
				str += separa;
				str += String
						.format("CPF: %s\n\nProcedimento: %s\nTipo: Consulta\n\nPreço: R$ %.2f\nRealização: %s\n\n",
								em.getUsuario().getCpf().getCpf(), em
										.getProcedimento().getNome(), em
										.getProcedimento().getPreco(), sdf
										.format(em.getDataEHora()));
				str += String.format("Medico:\n   Nome: %s\n   CRM:    %s\n", c
						.getMedico().getNome(), c.getMedico().getCrm());
			}
			receitaParc = em.getProcedimento().getPreco() * 0.20;
			receitaTot += receitaParc;
			str += String.format("\nVALOR A RECEBER: %.2f\n", receitaParc);
		}
		str += separa;
		str += String.format("\nTOTAL A RECEBER: R$ %.2f\n", receitaTot);
		return str;
	}

	@Override
	public String toString() {
		
		return this.getNome();
		
		/*String ret = String.format(
				"CNPJ: %s\nNome: %s\nEndereço: %s\nEspecialidades: ", getCnpj()
						.getCnpj(), getNome(), getEndereco().toString());
		for (Especialidade especialidade : getEspecialidades()) {
			ret += especialidade.toString();
		}
		ret += "\nMedicos Responsáveis: ";
		for (Medico medico : medicos) {
			ret += medico.toString();
		}
		return ret;*/
	}
}
