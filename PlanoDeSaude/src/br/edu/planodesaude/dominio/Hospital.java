package br.edu.planodesaude.dominio;

import java.util.ArrayList;

import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Endereco;

public class Hospital extends EstabelecimentoMedico {
	
	public Hospital(){
		this(null, null, null, null, false);
	}
	
	public Hospital(Cnpj cnpj, String nome, Endereco endereco,
			ArrayList<Medico> medicos, boolean ativo) {
		super(cnpj, nome, endereco, medicos, ativo);
	}
	
	public Hospital(Cnpj cnpj, String nome, Endereco endereco,
			ArrayList<Medico> medicos, ArrayList<Especialidade> especialidades, boolean ativo) {
		super(cnpj, nome, endereco, medicos, especialidades, ativo);
	}
}
