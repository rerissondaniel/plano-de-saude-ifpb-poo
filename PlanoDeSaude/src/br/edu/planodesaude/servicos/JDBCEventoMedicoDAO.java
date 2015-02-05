package br.edu.planodesaude.servicos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import br.edu.planodesaude.servicos.dao.EventoMedicoDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Conexao;

public class JDBCEventoMedicoDAO implements EventoMedicoDAO {

	@Override
	public int insert(EventoMedico eventoMedico) throws SQLException {

		String sql;

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet;

		if (eventoMedico instanceof Cirurgia) {
			Cirurgia evento = (Cirurgia) eventoMedico;

			sql = String
					.format("INSERT INTO EVENTO_MEDICO (id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) "
							+ "VALUES(%d, %d, '%s', '%s')", evento
							.getProcedimento().getCodigo(), evento.getUsuario()
							.getCodigo(), evento.getDataEHora().toString(),
							evento.getHospital().getCnpj().getCnpj());

			statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();

			int id = -1;

			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			if (id != -1) {

				for (Medico medico : evento.getMedicos()) {
					sql = String.format
						("INSERT INTO MEDICO_realiza_EVENTO_MEDICO (id_procedimento_realizado, crm_medico) VALUES(%d, '%s')",
									id, medico.getCrm());

					statement.execute(sql);
				}

				return id;
			}
			
		} else {
			Consulta evento = (Consulta) eventoMedico;
			sql = String
					.format("INSERT INTO EVENTO_MEDICO (id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) "
							+ "VALUES(%d, %d, '%s', '%s')", evento
							.getProcedimento().getCodigo(), evento.getUsuario()
							.getCodigo(), evento.getDataEHora().toString(),
							evento.getEstabelecimento().getCnpj().getCnpj());
			statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			int id = -1;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			if(id != -1){
				sql = String.format("INSERT INTO MEDICO_realiza_EVENTO_MEDICO (id_procedimento_realizado, crm_medico) VALUES(%d, '%s')",
						id, evento.getMedico().getCrm());
				statement.execute(sql);
			}
		}

		return MEDICAL_EVENT_NOT_FOUND;
	}

	@Override
	public ArrayList<EventoMedico> searchByDate(Date data) throws SQLException {
		String sql = String.format("SELECT * FROM EVENTO_MEDICO");

		Statement statementEvent = Conexao.getConexao().createStatement(), statementMed = Conexao
				.getConexao().createStatement();
		ResultSet resultSet = statementEvent.executeQuery(sql), resultMed;

		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		JDBCEstabelecimentoMedicoDAO jed = new JDBCEstabelecimentoMedicoDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();

		ArrayList<EventoMedico> list = new ArrayList<EventoMedico>();

		while (resultSet.next()) {
			Timestamp ts = resultSet.getTimestamp("data_realizacao");

			Date dt = new Date(ts.getTime());

			/*
			 * Pequena Gambiarra necessária: Quando tentamos comparar dois
			 * objetos do tipo Date algo acontece e o programa é finalizado,
			 * dessa forma criei dois objetos String que armazenam a data dos
			 * objetos Date, depois são comparadas
			 */
			String d1 = data.toString();
			String d2 = dt.toString();
	
			if (d1.equals(d2) == true) {
				sql = String
						.format("SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
								resultSet.getInt("id_procedimento_realizado"));
				resultMed = statementMed.executeQuery(sql);
				Procedimento proc = jpd.getProcedure(resultSet
						.getInt("id_procedimento"));
				if(proc.getTipo().equals("cirurgia")){
					/* Composição dos médicos que participaram do procedimento. */
					ArrayList<Medico> medicos = new ArrayList<Medico>();
		
					while (resultMed.next()) {
						medicos.add(jmd.getDoctor(resultMed.getString("crm_medico")));
					}
					/* Final da composição dos médicos. */
					list.add(new Cirurgia(
							resultSet.getInt("id_procedimento_realizado"),
							jud.getUser(resultSet.getInt("id_usuario")),
							ts,
							(Hospital) jed.getEstabelecimentoMedico(Cnpj
									.getInstance(resultSet
											.getString("cnpj_estabelecimento"))),
							medicos, jpd.getProcedure(resultSet
									.getInt("id_procedimento"))));
				}else{
					if(resultMed.next()){
						Medico medico = jmd.getDoctor(resultMed.getString("crm_medico"));
						list.add(new Consulta(
								resultSet.getInt("id_procedimento_realizado"),
								jud.getUser(resultSet.getInt("id_usuario")),
								ts,
								jed.getEstabelecimentoMedico(Cnpj
										.getInstance(resultSet
												.getString("cnpj_estabelecimento"))),
								medico, jpd.getProcedure(resultSet
										.getInt("id_procedimento"))));
					}else{
						throw new SQLException("Médico não cadastrado na base de dados."); 
					}
				}
				
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<EventoMedico> searchByDoctor(Medico medico)
			throws SQLException {

		String sql = String
				.format("SELECT id_procedimento_realizado FROM MEDICO_realiza_EVENTO_MEDICO WHERE crm_medico = '%s'",
						medico.getCrm());
		Statement statementEvent = Conexao.getConexao().createStatement(), statMed = Conexao
				.getConexao().createStatement();
		ResultSet resultSet = statementEvent.executeQuery(sql), resAux;

		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCEstabelecimentoMedicoDAO jed = new JDBCEstabelecimentoMedicoDAO();

		ArrayList<EventoMedico> ret = new ArrayList<EventoMedico>();

		while (resultSet.next()) {
			sql = String
					.format("SELECT * FROM EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
							resultSet.getInt("id_procedimento_realizado"));

			resAux = statMed.executeQuery(sql);

			resAux.next();

			Timestamp data = resAux.getTimestamp("data_realizacao");

			Procedimento procedimento = jpd.getProcedure(resAux
					.getInt("id_procedimento"));
			Usuario usuario = jud.getUser(resAux.getInt("id_usuario"));
			EstabelecimentoMedico estabelecimentoMedico = jed
					.getEstabelecimentoMedico(Cnpj.getInstance(resAux
							.getString("cnpj_estabelecimento")));

			if (procedimento.getTipo().equals("consulta")) {
				ret.add(new Consulta(resultSet
						.getInt("id_procedimento_realizado"), usuario, data,
						estabelecimentoMedico, medico, procedimento));
			} else {
				/* Construção da lista de médicos que participaram do procedimento */
				ArrayList<Medico> medicos = new ArrayList<Medico>();

				sql = String
						.format("SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
								resultSet.getInt("id_procedimento_realizado"));

				resAux = statMed.executeQuery(sql);

				while (resAux.next()) {
					medicos.add(jmd.getDoctor(resAux.getString("crm_medico")));
				}
				/* Final da construção da lista */

				ret.add(new Cirurgia(resultSet
						.getInt("id_procedimento_realizado"), usuario, data,
						(Hospital) estabelecimentoMedico, medicos, procedimento));
			}
		}

		return ret;
	}

	@Override
	public ArrayList<EventoMedico> searchBySpecialty(Especialidade especialidade)
			throws SQLException {
		ArrayList<EventoMedico> list = new ArrayList<EventoMedico>();
		
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();
		
		String sql = String.format("SELECT * FROM EVENTO_E_ESPECIALIDADE WHERE id_especialidade = %d", especialidade.getCodigo());
		Statement st = Conexao.getConexao().createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			list.add(jemd.getById(rs.getInt("id_procedimento_realizado")));
		}
		if(list.isEmpty())
			return null;	
		return list;
	}

	@Override
	public ArrayList<EventoMedico> searchByPatient(Usuario usuario)
			throws SQLException {
		/*
		 * Retorna o conjunto de eventos médicos realizado pelo usuário passado
		 * como argumento. Caso o usuário não tenha realizado nenhum evento
		 * médico retorna um ArrayList<EventoMedico> vazio.
		 */
		String sql = String.format(
				"SELECT * FROM EVENTO_MEDICO WHERE id_usuario = %d",
				usuario.getCodigo());
		Statement eventSt = Conexao.getConexao().createStatement(), medicosSt = Conexao
				.getConexao().createStatement();

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
			// Criando o ArrayList de Médicos.
			usAux = jud.getUser(eventRs.getInt("id_usuario"));
			sql = String
					.format("SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
							eventRs.getInt("id_procedimento_realizado"));
			medicosRs = medicosSt.executeQuery(sql);
			procedAux = jpd.getProcedure(eventRs.getInt("id_procedimento"));
			estabAux = jed.getEstabelecimentoMedico(Cnpj.getInstance(eventRs
					.getString("cnpj_estabelecimento")));
			medicosRs.next();
			Medico medico = jmd.getDoctor(medicosRs.getString("crm_medico"));
			if (procedAux.getTipo().equals("cirurgia")) {
				medicosAux = new ArrayList<Medico>();
				while (medicosRs.next()) {
					medicosAux
					.add(jmd.getDoctor(medicosRs.getString("crm_medico")));
				}
				ret.add(new Cirurgia(eventRs
						.getInt("id_procedimento_realizado"), usAux, eventRs
						.getTimestamp("data_realizacao"), (Hospital) estabAux,
						medicosAux, procedAux));
			} else {
				ret.add(new Consulta(eventRs
						.getInt("id_procedimento_realizado"), usAux, eventRs
						.getTimestamp("data_realizacao"), estabAux, medico,
						procedAux));
			}
		}

		return ret;
	}
	
	@Override
	public ArrayList<EventoMedico> getScheduledProcedures(Medico medico)
			throws SQLException {
		String sql = String.format(
				"SELECT * FROM MEDICOS_E_HORARIOS WHERE crm_medico = '%s'",
				medico.getCrm());
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		
		ArrayList<EventoMedico> ret = new ArrayList<EventoMedico>();
		JDBCEventoMedicoDAO jemd = new JDBCEventoMedicoDAO();

		while (rs.next()) {
			ret.add(jemd.getById(rs.getInt("id_procedimento_realizado")));
		}
		return ret;
	}
	
	@Override
	public EventoMedico getById(int idProcedimentoRealizado)
			throws SQLException {

		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCProcedimentoDAO jpd = new JDBCProcedimentoDAO();
		JDBCEstabelecimentoMedicoDAO jed = new JDBCEstabelecimentoMedicoDAO();
		Procedimento procedimento;
		Usuario usuario;
		EstabelecimentoMedico estabelecimento;

		String sql = String
				.format("SELECT * FROM EVENTO_MEDICO WHERE id_procedimento_realizado = %d;",
						idProcedimentoRealizado);

		Statement statement = Conexao.getConexao().createStatement(), statementMed = Conexao
				.getConexao().createStatement();
		ResultSet rs = statement.executeQuery(sql), rsMed;

		sql = String
				.format("SELECT * FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
						idProcedimentoRealizado);
		rsMed = statementMed.executeQuery(sql);
		if (rs.next()) {
			usuario = jud.getUser(rs.getInt("id_usuario"));
			procedimento = jpd.getProcedure(rs.getInt("id_procedimento"));
			estabelecimento = jed.getEstabelecimentoMedico(Cnpj.getInstance(rs
					.getString("cnpj_estabelecimento")));
			if (procedimento.getTipo().equals("cirurgia")) {
				ArrayList<Medico> medicos = new ArrayList<Medico>();
				while (rsMed.next()) {
					medicos.add(jmd.getDoctor(rsMed.getString("crm_medico")));
				}
				return new Cirurgia(idProcedimentoRealizado, usuario,
						rs.getTimestamp("data_realizacao"),
						(Hospital) estabelecimento, medicos, procedimento);
			} else {
				if(rsMed.next())
					return new Consulta(idProcedimentoRealizado, usuario,
							rs.getTimestamp("data_realizacao"), estabelecimento,
							jmd.getDoctor(rsMed.getString("crm_medico")), procedimento);
				else
					throw new SQLException("Médico não cadastrado na base de dados");
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<EventoMedico> searchByEstablishment(Cnpj cnpj)
			throws SQLException {
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		JDBCMedicoDAO jmd = new JDBCMedicoDAO();
		JDBCProcedimentoDAO jpd= new JDBCProcedimentoDAO();
		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		EstabelecimentoMedico estabelecimentoMedico = jemd.getEstabelecimentoMedico(cnpj);
		jemd = null;
		ArrayList<EventoMedico> list = new ArrayList<EventoMedico>();
		Usuario usuario;
		Medico medico;
		Procedimento procedimento;
		String sql = String
				.format("SELECT * FROM EVENTO_MEDICO WHERE cnpj_estabelecimento = '%s'",
						cnpj.getCnpj());
		Statement st = Conexao.getConexao().createStatement(), stMed = Conexao.getConexao().createStatement();
		ResultSet rs = st.executeQuery(sql), rsMed;
		
		while(rs.next()){
			usuario = jud.getUser(rs.getInt("id_usuario"));
			procedimento = jpd.getProcedure(rs.getInt("id_procedimento"));
			Timestamp dataEHora = rs.getTimestamp("data_realizacao");

			sql = String.format("SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d", rs.getInt("id_procedimento_realizado"));
			rsMed = stMed.executeQuery(sql);
			
			if(procedimento.getTipo().equals("consulta")){
				if(rsMed.next()){
					medico = jmd.getDoctor(rsMed.getString("crm_medico"));
					list.add(new Consulta(rs.getInt("id_procedimento_realizado"), usuario, dataEHora, estabelecimentoMedico, medico, procedimento));
				}
			}else{
				ArrayList<Medico> medicos = new ArrayList<Medico>();
				while(rsMed.next())
					medicos.add(jmd.getDoctor(rsMed.getString("crm_medico")));
				list.add(new Cirurgia(rs.getInt("id_procedimento_realizado"), usuario, dataEHora, (Hospital)estabelecimentoMedico, medicos, procedimento));
			}
		}
		if(list.isEmpty())return null;
		return list;
	}
}