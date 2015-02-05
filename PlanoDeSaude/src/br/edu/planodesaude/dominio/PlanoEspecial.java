package br.edu.planodesaude.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlanoEspecial extends Plano {

	private final String separa = "----------------------------------------------------------------------------------------------------------------------------------\n";;
	
	public PlanoEspecial(int cod, String descricao) {
		super(cod, descricao);
	}
	
	@Override
	public String geraFatura(Usuario us, ArrayList<EventoMedico> eventos){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		double precoPag, total = 0.0;
		String str = separa;
		str += String.format("\t\t\tFATURA MÉDICA - VIA DO USUÁRIO\n" + separa + "\nUsuário:\t  %s\nCPF:\t  %s\nCódigo:\t  %d\nPlano:\t  Especial\n\n", us.getNome(), us.getCpf(), us.getCodigo());
		for(EventoMedico em : eventos){
			if(em instanceof Cirurgia){
				Cirurgia c = (Cirurgia)em;
				str += separa;
				str += String.format("Procedimento: %s\nTipo: Cirurgia\n\nEstabelecimento: %s\nCNPJ: %s\n\nRealização: %s\nPreço: R$ %.2f\n\nMedico(s):\n", c.getProcedimento().getNome(), c.getHospital().getNome(), c.getHospital().getCnpj().getCnpj(), sdf.format(c.getDataEHora()), c.getProcedimento().getPreco());
				
				for(Medico med : c.getMedicos()){
					str += String.format("   Nome: %s\n   CRM: %s\n\n", med.getNome(), med.getCrm());
				}
			}else{
				Consulta cs = (Consulta)em;
				str += separa;
				str += String.format("Procedimento: %s\nTipo: Consulta\n\nEstabelecimento: %s\nCNPJ: %s\n\nRealização: %s\nPreço: R$ %.2f\n\n", cs.getProcedimento().getNome(), cs.getEstabelecimento().getNome(), cs.getEstabelecimento().getCnpj().getCnpj(), sdf.format(cs.getDataEHora()), cs.getProcedimento().getPreco());
				str += String.format("Medico:\n   Nome: %s\n   CRM: %s\n\n", cs.getMedico().getNome(), cs.getMedico().getCrm());
			}
			precoPag = em.getProcedimento().getPreco() * 0.01;
			total += precoPag;
			str += String.format("VALOR A PAGAR: %.2f\n", precoPag);
		}
		total += 1000.0;
		str += separa;
		str += String.format("\nTOTAL A PAGAR: R$ %.2f\n", total);
		
		return str;
	}
}
