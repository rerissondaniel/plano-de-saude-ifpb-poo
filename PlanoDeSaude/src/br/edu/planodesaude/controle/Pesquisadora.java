package br.edu.planodesaude.controle;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Cirurgia;
import br.edu.planodesaude.dominio.Consulta;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Hospital;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.servicos.JDBCEspecialidadeDAO;
import br.edu.planodesaude.servicos.JDBCEstabelecimentoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCEventoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCMedicoDAO;
import br.edu.planodesaude.servicos.JDBCProcedimentoDAO;
import br.edu.planodesaude.servicos.JDBCUsuarioDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Cpf;

public class Pesquisadora {
	
	private final String separa = "----------------------------------------------------------------------------------------------------------------------------------\n";;
	
	public EstabelecimentoMedico pesquisarEstabelecimento(String cnpjs)
			throws SearchException {
		Cnpj cnpj;
		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		EstabelecimentoMedico retEstab;
		try {
			cnpj = Cnpj.getInstance(cnpjs);
		} catch (IllegalArgumentException ilaex) {
			throw new SearchException("CNPJ Inválido!");
		}
		try {
			retEstab = jemd.getEstabelecimentoMedico(cnpj);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new SearchException("Estabelecimento não cadastrado!");
		}
		return retEstab;
	}

	public String pesquisarEventoMedico(Date data) throws SearchException {
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		ArrayList<EventoMedico> eventos;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String retStr = separa;
		retStr += String.format("\t\t::: PROCEDIMENTOS PARA %s:::\n\n",
				new SimpleDateFormat("dd/MM/yyyy").format(data));
		try {
			eventos = jemd.searchByDate(data);
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		if (eventos.isEmpty())
			throw new SearchException(
					"Nenhum procedimento encontrado para esta data!");
		for (EventoMedico ev : eventos) {
			if (ev instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) ev;
				retStr += separa;

				retStr += String
						.format("Procedimento: %s\nTipo: Cirurgia\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico(s):\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c.getHospital()
										.getNome(), c.getUsuario().getNome());
				for (Medico med : c.getMedicos()) {
					retStr += String.format("   Nome: %s\n   CRM:   %s\n",
							med.getNome(), med.getCrm());
				}
			} else {
				Consulta c = (Consulta) ev;
				retStr += separa;
				retStr += String
						.format("Procedimento: %s\nTipo: Consulta\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico:\n   Nome: %s\n   CRM:   %s\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c
										.getEstabelecimento().getNome(), c
										.getUsuario().getNome(), c.getMedico()
										.getNome(), c.getMedico().getCrm());

			}
		}
		retStr += separa;
		return retStr;
	}

	public String pesquisarEventoMedico(Medico medico) throws SearchException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		ArrayList<EventoMedico> eventos;
		
		String retStr = separa;
		retStr += String.format(
				"\t\t::: PROCEDIMENTOS PARA %s :: CRM: %s:::\n\n",
				medico.getNome(), medico.getCrm());
		try {
			eventos = jemd.searchByDoctor(medico);
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		if (eventos.isEmpty())
			throw new SearchException(
					"Nenhum procedimento encontrado para esta médico!");

		for (EventoMedico ev : eventos) {
			if (ev instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) ev;
				retStr += separa;

				retStr += String
						.format("Procedimento: %s\nTipo: Cirurgia\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico(s):\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c.getHospital()
										.getNome(), c.getUsuario().getNome());
				for (Medico med : c.getMedicos()) {
					retStr += String.format("   Nome: %s\n   CRM:   %s\n",
							med.getNome(), med.getCrm());
				}
			} else {
				Consulta c = (Consulta) ev;
				retStr += separa;
				retStr += String
						.format("Procedimento: %s\nTipo: Consulta\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico:\n   Nome: %s\n   CRM:   %s\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c
										.getEstabelecimento().getNome(), c
										.getUsuario().getNome(), c.getMedico()
										.getNome(), c.getMedico().getCrm());

			}
		}
		retStr += separa;
		return retStr;
	}

	public String pesquisarEventoMedico(Especialidade especialidade)
			throws SearchException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		ArrayList<EventoMedico> eventos;
		String retStr = separa;
		retStr += String.format(
				"\t\t::: PROCEDIMENTOS PARA A ESPECIALIDADE %s :::\n\n",
				especialidade.getNome());
		try {
			eventos = jemd.searchBySpecialty(especialidade);
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		if (eventos == null)
			return null;
		for (EventoMedico ev : eventos) {
			if (ev instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) ev;
				retStr += separa;

				retStr += String
						.format("Procedimento: %s\nTipo: Cirurgia\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico(s):\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c.getHospital()
										.getNome(), c.getUsuario().getNome());
				for (Medico med : c.getMedicos()) {
					retStr += String.format("   Nome: %s\n   CRM:   %s\n",
							med.getNome(), med.getCrm());
				}
			} else {
				Consulta c = (Consulta) ev;
				retStr += separa;
				retStr += String
						.format("Procedimento: %s\nTipo: Consulta\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico:\n   Nome: %s\n   CRM:   %s\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c
										.getEstabelecimento().getNome(), c
										.getUsuario().getNome(), c.getMedico()
										.getNome(), c.getMedico().getCrm());

			}
		}
		retStr += separa;
		return retStr;
	}

	public String pesquisarEventoMedico(Usuario usuario) throws SearchException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String retStr = String.format(
				"\t::: PROCEDIMENTOS PARA O USUÁRIO: %s CPF: %s :::\n\n",
				usuario.getNome(), usuario.getCpf().getCpf());
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		ArrayList<EventoMedico> eventos;
		try {
			eventos = jemd.searchByPatient(usuario);
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		if (eventos.isEmpty())
			throw new SearchException(
					"Nenhum procedimento encontrado para este usuário!");
		for (EventoMedico ev : eventos) {
			if (ev instanceof Cirurgia) {
				Cirurgia c = (Cirurgia) ev;
				retStr += separa;

				retStr += String
						.format("Procedimento: %s\nTipo: Cirurgia\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico(s):\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c.getHospital()
										.getNome(), c.getUsuario().getNome());
				for (Medico med : c.getMedicos()) {
					retStr += String.format("   Nome: %s\n   CRM:   %s\n",
							med.getNome(), med.getCrm());
				}
			} else {
				Consulta c = (Consulta) ev;
				retStr += separa;
				retStr += String
						.format("Procedimento: %s\nTipo: Consulta\n\nRealização: %s\n\nEstabelecimento: %s\n\nUsuário: %s\n\nMédico:\n   Nome: %s\n   CRM:   %s\n",
								c.getProcedimento().getNome(), sdf.format(c
										.getDataEHora()), c
										.getEstabelecimento().getNome(), c
										.getUsuario().getNome(), c.getMedico()
										.getNome(), c.getMedico().getCrm());

			}
		}
		retStr += separa;
		return retStr;
	}

	public Procedimento pesquisarProcedimento(String nomeProcedimento)
			throws SearchException {
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		Procedimento procedimento = null;
		try {
			procedimento = jpd.getProcedure(nomeProcedimento);
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		if (procedimento == null)
			throw new SearchException(
					"Procedimento não cadastrado na base de dados!");
		return procedimento;
	}

	public Usuario pesquisarUsuario(String str) throws SearchException {
		Cpf cpf;
		Usuario ret;
		try {
			cpf = Cpf.getInstance(str);
		} catch (IllegalArgumentException ilaex) {
			throw new SearchException("CPF inválido!");
		}
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		try {
			ret = jud.getUser(cpf);
			if (ret == null)
				throw new SearchException(
						"Usuário não cadastrado na base de dados!");
		} catch (SQLException sqlex) {
			throw new SearchException(sqlex.getMessage());
		}
		return ret;
	}

	public Medico pesquisarMedico(String crm) throws SearchException {
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		Medico ret = null;
		try {
			ret = jmd.getDoctor(crm);
			if (ret == null)
				throw new SearchException(
						"Médico não encontrado na base de dados!");
		} catch (SQLException sqlex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		return ret;
	}

	public ArrayList<Especialidade> getEspecialidades() throws SearchException {
		JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();
		ArrayList<Especialidade> ret = new ArrayList<Especialidade>();

		try {
			ret = jed.getAll();

			if (ret.size() == 0)
				throw new SearchException(
						"Nenhuma especialidade encontra-se cadastrada na base de dados, favor cadastrar.");
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return ret;
	}

	public ArrayList<Medico> getMedicos() throws SearchException {
		JDBCMedicoDAO jed = new JDBCMedicoDAO();
		ArrayList<Medico> ret = new ArrayList<Medico>();

		try {
			ret = jed.getAll();

			if (ret.size() == 0)
				throw new SearchException(
						"Nenhum médico encontra-se cadastrado na base de dados, favor cadastrar.");
		} catch (SQLException ex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return ret;
	}

	public ArrayList<EstabelecimentoMedico> getHospitais()
			throws SearchException {
		ArrayList<EstabelecimentoMedico> tudo = this.getEstabelecimentos(), retEstab = new ArrayList<EstabelecimentoMedico>();

		for (int i = 0; i < tudo.size(); i++)
			if (tudo.get(i) instanceof Hospital)
				retEstab.add(tudo.get(i));

		return retEstab;
	}

	public ArrayList<EstabelecimentoMedico> getEstabelecimentos()
			throws SearchException {

		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		ArrayList<EstabelecimentoMedico> estabelecimentos;
		try {
			estabelecimentos = jemd.getAll();
			if (estabelecimentos.isEmpty())
				throw new SearchException(
						"Nenhum estabelecimento médico cadastrado na base de dados, favor cadastrar.");
		} catch (SQLException ex) {
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return estabelecimentos;
	}

	public ArrayList<Procedimento> getProcedimentos() throws SearchException {
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		ArrayList<Procedimento> ret = new ArrayList<Procedimento>();

		try {
			ret = jpd.getAll();

			if (ret.size() == 0)
				throw new SearchException(
						"Nenhum procedimento encontra-se cadastrado na base de dados, favor cadastrar.");
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new SearchException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return ret;
	}
	
	public ArrayList<Procedimento> getCirurgias()
			throws SearchException {
		ArrayList<Procedimento> tudo = this.getProcedimentos(), retProc = new ArrayList<Procedimento>();

		for (int i = 0; i < tudo.size(); i++)
			if (tudo.get(i).getTipo().equals("cirurgia"))
				retProc.add(tudo.get(i));

		return retProc;
	}
	
	public ArrayList<Procedimento> getConsultas()
			throws SearchException {
		ArrayList<Procedimento> tudo = this.getProcedimentos(), retProc = new ArrayList<Procedimento>();

		for (int i = 0; i < tudo.size(); i++)
			if (tudo.get(i).getTipo().equals("consulta"))
				retProc.add(tudo.get(i));

		return retProc;
	}
	
}