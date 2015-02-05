package br.edu.planodesaude.controle;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Cirurgia;
import br.edu.planodesaude.dominio.Consulta;
import br.edu.planodesaude.dominio.Consultorio;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Hospital;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Plano;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.DoctorNotFoundException;
import br.edu.planodesaude.exceptions.InsertionException;
import br.edu.planodesaude.servicos.JDBCEspecialidadeDAO;
import br.edu.planodesaude.servicos.JDBCEstabelecimentoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCEventoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCMedicoDAO;
import br.edu.planodesaude.servicos.JDBCPlanoDAO;
import br.edu.planodesaude.servicos.JDBCProcedimentoDAO;
import br.edu.planodesaude.servicos.JDBCUsuarioDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Cpf;
import br.edu.planodesaude.util.Endereco;

public class Cadastradora {

	public boolean cadastrarHospital(Cnpj cnpj, String nome, String rua,
			String numero, String bairro, String cidade, String referencia,
			String cep, String estado, ArrayList<String> crms,
			ArrayList<Especialidade> especialidades) throws InsertionException,
			DoctorNotFoundException {

		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();

		Endereco endereco = new Endereco(rua, numero, bairro, cidade,
				referencia, cep, estado);

		ArrayList<Medico> medicos = new ArrayList<Medico>();
		
		try {
			for (String crm : crms) {
				medicos.add(jmd.getDoctor(crm));
			}

			Hospital hospital = new Hospital(cnpj, nome, endereco, medicos,
					especialidades, true);

			jemd.insert(hospital);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			if (sqlex.getMessage().contains("Duplicate entry")) {
				throw new InsertionException("CNPJ já cadastrado!");
			} else {
				throw new InsertionException(
						"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
			}
		}

		return true;
	}

	public boolean cadastrarClinica(Cnpj cnpj, String nome, String rua,
			String numero, String bairro, String cidade, String referencia,
			String cep, String estado,
			ArrayList<String> crms, ArrayList<Especialidade> especialidades)
			throws InsertionException {

		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		Endereco endereco = new Endereco(rua, numero, bairro, cidade,
				referencia, cep, estado);
		
		try {
			for (String aux : crms) {
				medicos.add(jmd.getDoctor(aux));
			}

			Consultorio consultorio = new Consultorio(cnpj, nome, endereco,
					medicos, especialidades, true);

			jemd.insert(consultorio);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			if (sqlex.getMessage().contains("Duplicate entry")) {
				throw new InsertionException("CNPJ já cadastrado!");
			} else {
				throw new InsertionException(
						"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
			}
		}
		return true;
	}

	public boolean cadastrarEspecialidade(String nome, String descricao)
			throws InsertionException {
		Especialidade especialidade = new Especialidade(nome, descricao);
		JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();
		try {
			jed.insert(especialidade);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new InsertionException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		return true;
	}

	public boolean cadastrarMedico(String crm, String nome,
			ArrayList<Especialidade> especialidades) throws InsertionException {

		JDBCMedicoDAO jmd = new JDBCMedicoDAO();

		Medico medico = new Medico(crm, nome, especialidades, true);
		try {
			medico.setCrm(jmd.insert(medico));
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			if (sqlex.getMessage().contains("Duplicate entry")) {
				throw new InsertionException(
						"CRM Inválido! Há outro médico com esse CRM.");
			} else if (sqlex.getMessage().contains("Data truncation")) {
				throw new InsertionException("CRM muito longo!");
			}
			throw new InsertionException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return true;
	}

	public boolean cadastrarUsuario(String cpfs, String nome, String telefone,
			String rua, String numero, String bairro, String cidade,
			String referencia, String cep, String estado, String planoS)
			throws InsertionException {

		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCPlanoDAO jpd = new JDBCPlanoDAO();
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		Cpf cpf;
		try{
			cpf = Cpf.getInstance(cpfs);
		}catch(IllegalArgumentException ilaex){
			throw new InsertionException("CPF Inválido!");
		}
		
		try {
			if (jemd.getAll().size() == 0)
				throw new InsertionException(
						"Nenhum Hospital ou clínica cadastrado!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertionException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		Endereco endereco = new Endereco(rua, numero, bairro, cidade, referencia, cep,
				estado);
		
		Plano plano = null;

		try{
			if (planoS.equals("basico")) {
				plano = jpd.getPlano(1);
			} else if(planoS.equals("especial")){
				plano = jpd.getPlano(2);
			}
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			throw new InsertionException("Plano não cadastrado.");
		}

		Usuario usuario = new Usuario(cpf, nome, telefone, endereco, true,
				date, plano);
		try {
			usuario.setCodigo(jud.insert(usuario));
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			if (sqlex.getMessage().contains("Duplicate entry")) {
				throw new InsertionException("Entrada de CPF Duplicada!");
			}
		}

		if (usuario.getCodigo() == JDBCUsuarioDAO.USER_NOT_INSERTED)
			throw new InsertionException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");

		return true;
	}

	public boolean marcarCirurgia(Usuario us, Hospital hosp, 
			ArrayList<Medico> medicos, Procedimento proced, Timestamp data)
			throws InsertionException {
		ArrayList<EventoMedico> eventosMedicos;
		String dataStr = data.toString();
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		if(data.getTime() == System.currentTimeMillis())throw new InsertionException("Tá morrendo?");
		if(data.getTime() < System.currentTimeMillis())throw new InsertionException("Ainda tá vivo?");
		try {
			for (Medico medAux : medicos) {// Verificação se os médicos têm
				// algum outro procedimento marcado
				// para aquele horário
				eventosMedicos = jemd.getScheduledProcedures(medAux);
				for (EventoMedico evm : eventosMedicos) {
					if (dataStr.substring(0, 13).equals(evm.getDataEHora().toString().substring(0, 13)))
						throw new InsertionException(String.format("O médico %s, CRM %s está ocupado neste horário!", medAux.getNome(), medAux.getCrm()));
				}
			}
			EventoMedico evntMedico = new Cirurgia(us, data, hosp,
					medicos, proced);
			jemd.insert(evntMedico);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new InsertionException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return true;
	}
	
	public boolean marcarConsulta(Usuario us, EstabelecimentoMedico estab, Medico medico, Procedimento proc, Timestamp data) throws InsertionException {
		
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		
		String dataStr = data.toString();
		
		if(data.getTime() == System.currentTimeMillis())throw new InsertionException("Tá morrendo?");
		if(data.getTime() < System.currentTimeMillis())throw new InsertionException("Ainda tá vivo?");
		
		try {
			
			ArrayList<EventoMedico> eventosMedicos = jemd.getScheduledProcedures(medico);
			
			for (EventoMedico evm : eventosMedicos) {
				if (dataStr.equals(evm.getDataEHora().toString()))
					throw new InsertionException(String.format("O médico %s, CRM %s está ocupado neste horário!", medico.getNome(), medico.getCrm()));
			}
			
			EventoMedico evntMedico = new Consulta(us, data, estab,
					medico, proc);
			
			jemd.insert(evntMedico);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new InsertionException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}

		return true;
	}
	
	public boolean cadastrarProcedimento(String nome, float preco, String tipo, Especialidade especialidade) throws InsertionException {
		
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		Procedimento procedimento = new Procedimento(preco, nome, tipo, especialidade);
		
		try{
			procedimento.setCodigo(jpd.insert(procedimento));
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			throw new InsertionException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		
		return true;
	}
}