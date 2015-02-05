package br.edu.planodesaude.controle;

import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.UpdateException;
import br.edu.planodesaude.servicos.JDBCEstabelecimentoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCMedicoDAO;
import br.edu.planodesaude.servicos.JDBCProcedimentoDAO;
import br.edu.planodesaude.servicos.JDBCUsuarioDAO;

public class Alteradora {

	public boolean alterarMedico(String crm, String nome,
			ArrayList<Especialidade> especialidades, boolean credenciado)
			throws UpdateException {
		Medico medico = new Medico(crm, nome, especialidades, credenciado);
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		try {
			jmd.update(medico);
		} catch (SQLException sqlex) {
			if (sqlex.getMessage().contains("Duplicate entry"))
				throw new UpdateException("CRM duplicado!");
			throw new UpdateException(
					"Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		return true;
	}

	public boolean alterarEstabelecimento(EstabelecimentoMedico estab) throws UpdateException{
		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		System.out.println(estab.isCredenciado());
		try{
			jemd.update(estab);
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			throw new UpdateException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		return true;
	}

	public boolean alterarUsuario(Usuario usuario) throws UpdateException{
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		try {
			jud.update(usuario);
		} catch (SQLException sqlException) {
			if (sqlException.getMessage().contains("Duplicate entry")) {
				throw new UpdateException("Entrada de CPF Duplicada!");
			}
		}
		return true;
		
	}
	
	public boolean alterarProcedimento(Procedimento procedimento) throws UpdateException {
		
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		
		try {
			jpd.update(procedimento);
		} catch (SQLException e) {
			throw new UpdateException("Problema na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		
		return false;
	}

}
