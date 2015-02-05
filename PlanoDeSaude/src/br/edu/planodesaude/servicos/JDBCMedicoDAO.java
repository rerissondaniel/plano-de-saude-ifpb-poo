package br.edu.planodesaude.servicos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import br.edu.planodesaude.servicos.dao.MedicoDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Conexao;

public class JDBCMedicoDAO implements MedicoDAO {
	
	@Override
	public ArrayList<Medico> getAll() throws SQLException {

		JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();

		ArrayList<Medico> list = new ArrayList<Medico>();

		String sql = "SELECT * FROM MEDICO WHERE credenciado = true ORDER BY nome";

		Statement medicoStatement = Conexao.getConexao().createStatement();
		Statement medEspeStatement = Conexao.getConexao().createStatement();
		ResultSet resultSet, medEspeSet;

		resultSet = medicoStatement.executeQuery(sql);

		while (resultSet.next()) {
			String crm = resultSet.getString("crm");
			String nome = resultSet.getString("nome");
			boolean credenciado = resultSet.getBoolean("credenciado");

			ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();

			sql = String.format("SELECT id_especialidade FROM MEDICO_tem_ESPECIALIDADE WHERE crm_medico = '%s'", crm);

			medEspeSet = medEspeStatement.executeQuery(sql);

			while (medEspeSet.next()) {
				especialidades.add(jed.getSpecialty(medEspeSet.getInt("id_especialidade")));
			}

			list.add(new Medico(crm, nome, especialidades, credenciado));
		}

		return list;
	}

	@Override
	public Medico getDoctor(String crm) throws SQLException {

		JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();

		String sql = String.format("SELECT * FROM MEDICO WHERE crm = '%s'", crm);

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String nome = resultSet.getString("nome");
			boolean credenciado = resultSet.getBoolean("credenciado");
			ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();

			sql = String.format("SELECT * FROM MEDICO_tem_ESPECIALIDADE WHERE crm_medico = '%s'", crm);

			Statement espStatement = Conexao.getConexao().createStatement();
			ResultSet espResultSet = espStatement.executeQuery(sql);

			while (espResultSet.next()) {
				especialidades.add(jed.getSpecialty(espResultSet.getInt("id_especialidade")));
			}

			return new Medico(crm, nome, especialidades, credenciado);
		}

		return null;
	}

	@Override
	public String insert(Medico medico) throws SQLException {

		String sql = String.format("INSERT INTO MEDICO VALUES('%s', '%s', true);", medico.getCrm(), medico.getNome());

		Statement medicoStatement = Conexao.getConexao().createStatement();
		Statement medEspeStatement = Conexao.getConexao().createStatement();

		medicoStatement.execute(sql);
			
		for (Especialidade especialidade : medico.getEspecialidades()) {
			sql = String.format("INSERT INTO MEDICO_tem_ESPECIALIDADE VALUES('%s', %d);", medico.getCrm(),
					especialidade.getCodigo());

			medEspeStatement.executeUpdate(sql);
		}
			
		return medico.getCrm();
	}

	@Override
	public boolean update(Medico medico) throws SQLException {

		Statement medicoStatement = Conexao.getConexao().createStatement();
		String sql = String.format("UPDATE MEDICO SET nome = '%s', credenciado = " + medico.isCredenciado()
				+ " WHERE crm = '%s';", medico.getNome(), medico.getCrm());
		
		medicoStatement.executeUpdate(sql);
		
		sql = String.format("DELETE FROM MEDICO_tem_ESPECIALIDADE WHERE crm_medico = '%s'", medico.getCrm());
		
		medicoStatement.execute(sql);
		for(Especialidade especialidade : medico.getEspecialidades()){
			sql = String.format("INSERT INTO MEDICO_tem_ESPECIALIDADE(crm_medico,"
					+ " id_especialidade) VALUES('%s', %d)", medico.getCrm(), especialidade.getCodigo());
			medicoStatement.execute(sql);
		}
	
	    return true;
	}
	
	@Override
	public ArrayList<EventoMedico> getMedicalEventOnInterval(Medico medico, Date begin, Date end) throws SQLException {
		String sql = String
				.format("SELECT * FROM EVENTO_MEDICO event_med, MEDICO_realiza_EVENTO_MEDICO med_r_event " 
						+ "WHERE data_realizacao >= '%s' AND data_realizacao <= '%s' "
						+ "AND event_med.id_procedimento_realizado = med_r_event.id_procedimento_realizado "
						+ "AND med_r_event.crm_medico = '%s'", begin.toString() + " 00:00:00",
						end.toString() + " 23:59:59", medico.getCrm());

		Statement eventSt = Conexao.getConexao().createStatement(), medicosSt = Conexao.getConexao().createStatement();
		
		ArrayList<EventoMedico> ret = new ArrayList<EventoMedico>();

		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCEstabelecimentoMedicoDAO jed = new JDBCEstabelecimentoMedicoDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();

		Usuario usAux;
		ArrayList<Medico> medicosAux;
		Procedimento procedAux;
		EstabelecimentoMedico estabAux;

		ResultSet eventRs = eventSt.executeQuery(sql);
		ResultSet medicosRs;
		
		
		while (eventRs.next()) {
			usAux = jud.getUser(eventRs.getInt("id_usuario"));
			procedAux = jpd.getProcedure(eventRs.getInt("id_procedimento"));
			estabAux = jed.getEstabelecimentoMedico(Cnpj.getInstance(eventRs.getString("cnpj_estabelecimento")));
			if (procedAux.getTipo().equals("cirurgia")) {
				// Criando o ArrayList de Médicos.
				sql = String.format(
						"SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
						eventRs.getInt("id_procedimento_realizado"));
				medicosRs = medicosSt.executeQuery(sql);
				medicosAux = new ArrayList<Medico>();
				while (medicosRs.next()) {
					medicosAux.add(jmd.getDoctor(medicosRs.getString("crm_medico")));
				}
				ret.add(new Cirurgia(eventRs.getInt("id_procedimento_realizado"), usAux, eventRs
						.getTimestamp("data_realizacao"), (Hospital) estabAux, medicosAux, procedAux));
			} else {
				Medico medAux = jmd.getDoctor(eventRs.getString("crm_medico"));
				ret.add(new Consulta(eventRs.getInt("id_procedimento_realizado"), usAux, eventRs
						.getTimestamp("data_realizacao"), estabAux, medAux, procedAux));
			}
		}

		return ret;
	}
}