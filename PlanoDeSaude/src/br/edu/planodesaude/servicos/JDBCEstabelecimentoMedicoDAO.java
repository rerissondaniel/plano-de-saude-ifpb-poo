package br.edu.planodesaude.servicos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Cirurgia;
import br.edu.planodesaude.dominio.Consulta;
import br.edu.planodesaude.dominio.Consultorio;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Hospital;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.servicos.dao.EstabelecimentoMedicoDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Conexao;
import br.edu.planodesaude.util.Endereco;

public class JDBCEstabelecimentoMedicoDAO implements EstabelecimentoMedicoDAO {
	
	@Override
	public ArrayList<EstabelecimentoMedico> getAll() throws SQLException {

		ArrayList<EstabelecimentoMedico> estabelecimentos = new ArrayList<EstabelecimentoMedico>();

		String sql = "SELECT * FROM ESTABELECIMENTO_MEDICO WHERE credenciado = true ORDER BY nome";

		Statement statEstabelecimento = Conexao.getConexao().createStatement(), statEspecialidade = Conexao
				.getConexao().createStatement();

		ResultSet resEstabelecimento = statEstabelecimento.executeQuery(sql);

		ResultSet resEspecialidade;

		while (resEstabelecimento.next()) {
			if(resEstabelecimento.getBoolean("credenciado")){
				// Construção do estabelecimento médico
				Cnpj cnpj = Cnpj.getInstance(resEstabelecimento
						.getString("cnpj_estabelecimento"));
	
				String tipoEstabelecimento = resEstabelecimento
						.getString("tipo_estabelecimento"), nome = resEstabelecimento
						.getString("nome");
				Endereco endereco = new Endereco(
						resEstabelecimento.getString("rua"),
						resEstabelecimento.getString("numero"),
						resEstabelecimento.getString("bairro"),
						resEstabelecimento.getString("cidade"),
						resEstabelecimento.getString("referencia"),
						resEstabelecimento.getString("cep"),
						resEstabelecimento.getString("estado"));
				boolean credenciado = resEstabelecimento.getBoolean("credenciado");
	
				// Construção das especialidades do estabelecimento
				sql = String
						.format("SELECT id_especialidade FROM ESTABELECIMENTO_tem_ESPECIALIDADE WHERE cnpj_estabelecimento_medico = '%s'",
								cnpj.getCnpj());
				resEspecialidade = statEspecialidade.executeQuery(sql);
	
				ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
				ArrayList<Medico> medicos = new ArrayList<Medico>();
	
				JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();
				JDBCMedicoDAO jmd = new JDBCMedicoDAO();
	
				while (resEspecialidade.next()) {
					especialidades.add(jed.getSpecialty(resEspecialidade
							.getInt("id_especialidade")));
				}
	
				sql = String
						.format("SELECT crm_medico FROM MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO WHERE cnpj_estabelecimento = '%s'",
								cnpj.getCnpj());
	
				resEspecialidade = statEspecialidade.executeQuery(sql);
	
				while (resEspecialidade.next()) {
					medicos.add(jmd.getDoctor(resEspecialidade
							.getString("crm_medico")));
				}
	
				if (tipoEstabelecimento.equals("consultorio")) {
					estabelecimentos.add(new Consultorio(cnpj, nome, endereco,
							medicos, especialidades, credenciado));
				} else {
					estabelecimentos.add(new Hospital(cnpj, nome, endereco,
							medicos, especialidades, credenciado));
				}
			}
		}

		return estabelecimentos;
	}

	@Override
	public boolean insert(EstabelecimentoMedico estabelecimentoMedico)
			throws SQLException {
		String sql;
		Statement st = Conexao.getConexao().createStatement();
		sql = String
				.format("INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) "
						+ "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
						estabelecimentoMedico.getCnpj().getCnpj(),
						estabelecimentoMedico.getNome(),
						estabelecimentoMedico.getEndereco().getRua(),
						estabelecimentoMedico.getEndereco().getNumero(),
						estabelecimentoMedico.getEndereco().getBairro(),
						estabelecimentoMedico.getEndereco().getCidade(),
						estabelecimentoMedico.getEndereco().getReferencia(),
						estabelecimentoMedico.getEndereco().getCep(),
						estabelecimentoMedico.getEndereco().getEstado(),
						estabelecimentoMedico instanceof Consultorio ? "consultorio"
								: "hospital");
		st.execute(sql);
		
		for (Medico medico : estabelecimentoMedico.getMedico()) {
			sql = String
					.format("INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('%s', '%s')",
							medico.getCrm(), estabelecimentoMedico.getCnpj()
									.getCnpj());
			st.execute(sql);
		}
		
		for(Especialidade especialidade : estabelecimentoMedico.getEspecialidades()){
			sql = String.format("INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE(cnpj_estabelecimento_medico,"
					+ " id_especialidade) VALUES('%s', %d)", estabelecimentoMedico.getCnpj().getCnpj(), especialidade.getCodigo());
			st.execute(sql);
		}

		return true;
	}

	@Override
	public boolean update(EstabelecimentoMedico estabelecimentoMedico)
			throws SQLException {

		String sql = String
				.format("UPDATE ESTABELECIMENTO_MEDICO SET credenciado = "
						+ estabelecimentoMedico.isCredenciado()
						+ ", nome = '%s', rua = '%s', numero = '%s', bairro = '%s', cidade = '%s', referencia = '%s', cep = '%s', estado = '%s', tipo_estabelecimento = '%s' WHERE cnpj_estabelecimento = '%s'",
						estabelecimentoMedico.getNome(),
						estabelecimentoMedico.getEndereco().getRua(),
						estabelecimentoMedico.getEndereco().getNumero(),
						estabelecimentoMedico.getEndereco().getBairro(),
						estabelecimentoMedico.getEndereco().getCidade(),
						estabelecimentoMedico.getEndereco().getReferencia(),
						estabelecimentoMedico.getEndereco().getCep(),
						estabelecimentoMedico.getEndereco().getEstado(),
						estabelecimentoMedico instanceof Consultorio ? "consultorio"
								: "hospital", estabelecimentoMedico.getCnpj()
								.getCnpj());
		
		System.out.println(sql);
		
		Statement st = Conexao.getConexao().createStatement();
		st.execute(sql);
		/*Aqui temos uma gambiarra de última hora: Deletamos a coisa toda na tabela ESTABELECIMENTO_tem_ESPECIALIDADE e adicionamos de novo.
		 *  Há alguma forma mais elegante? Não sei. É o que temos pra hoje :) */
		
		sql = String.format("DELETE FROM ESTABELECIMENTO_tem_ESPECIALIDADE WHERE cnpj_estabelecimento_medico = '%s'", estabelecimentoMedico.getCnpj().getCnpj());
		
		st.execute(sql);
		for(Especialidade especialidade : estabelecimentoMedico.getEspecialidades()){
			sql = String.format("INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE(cnpj_estabelecimento_medico,"
					+ " id_especialidade) VALUES('%s', %d)", estabelecimentoMedico.getCnpj().getCnpj(), especialidade.getCodigo());
			st.execute(sql);
		}
		
		sql = String.format("DELETE FROM MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO WHERE cnpj_estabelecimento = '%s'", estabelecimentoMedico.getCnpj().getCnpj());
		
		st.execute(sql);
		for(Medico medico : estabelecimentoMedico.getMedico()){
			sql = String.format("INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO(cnpj_estabelecimento,"
					+ " crm_medico) VALUES('%s', '%s');", estabelecimentoMedico.getCnpj().getCnpj(), medico.getCrm());
			st.execute(sql);
		}
		
		
		return true;
	}

	@Override
	public EstabelecimentoMedico getEstabelecimentoMedico(Cnpj cnpj)
			throws SQLException {

		String sql = String
				.format("SELECT * FROM ESTABELECIMENTO_MEDICO WHERE cnpj_estabelecimento = '%s'",
						cnpj.getCnpj());

		JDBCMedicoDAO jmd = new JDBCMedicoDAO();

		Statement statEstabelecimento = Conexao.getConexao().createStatement(), statEspecialidade = Conexao
				.getConexao().createStatement();
		ResultSet resEstabelecimento = statEstabelecimento.executeQuery(sql), resEspecialidade;

		if (resEstabelecimento.next()) {

			Endereco endereco = new Endereco(
					resEstabelecimento.getString("rua"),
					resEstabelecimento.getString("numero"),
					resEstabelecimento.getString("bairro"),
					resEstabelecimento.getString("cidade"),
					resEstabelecimento.getString("referencia"),
					resEstabelecimento.getString("cep"),
					resEstabelecimento.getString("estado"));
			boolean credenciado = resEstabelecimento.getBoolean("credenciado");

			sql = String
					.format("SELECT * FROM ESTABELECIMENTO_tem_ESPECIALIDADE WHERE cnpj_estabelecimento_medico = '%s'",
							cnpj.getCnpj());

			resEspecialidade = statEspecialidade.executeQuery(sql);

			ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
			JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();

			while (resEspecialidade.next()) {
				especialidades.add(jed.getSpecialty(resEspecialidade
						.getInt("id_especialidade")));
			}

			ArrayList<Medico> medicos = new ArrayList<Medico>();
			Statement st = Conexao.getConexao().createStatement();
			ResultSet rs;

			sql = String
					.format("SELECT crm_medico FROM MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO WHERE cnpj_estabelecimento = '%s'",
							resEstabelecimento
									.getString("cnpj_estabelecimento"));

			rs = st.executeQuery(sql);

			while (rs.next()) {
				medicos.add(jmd.getDoctor(rs.getString("crm_medico")));
			}

			if (resEstabelecimento.getString("tipo_estabelecimento").equals(
					"consultorio")) {
				return new Consultorio(Cnpj.getInstance(resEstabelecimento
						.getString("cnpj_estabelecimento")),
						resEstabelecimento.getString("nome"), endereco,
						medicos, especialidades, credenciado);
			} else {
				return new Hospital(Cnpj.getInstance(resEstabelecimento
						.getString("cnpj_estabelecimento")),
						resEstabelecimento.getString("nome"), endereco,
						medicos, especialidades, credenciado);
			}
		}

		return null;
	}
			
	@Override
	public ArrayList<EventoMedico> getProceduresOnInterval(
			EstabelecimentoMedico estabelecimento, Date begin, Date end)
			throws SQLException {
		String sql = String.format("SELECT * FROM EVENTO_MEDICO event_med "
				+ "WHERE data_realizacao BETWEEN '%s' AND '%s' "
				+ "AND event_med.cnpj_estabelecimento = '%s' ", begin.toString()
				+ " 00:00:00", end.toString() + " 23:59:59",
				estabelecimento.getCnpj().getCnpj());

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
			sql = String
					.format("SELECT crm_medico FROM MEDICO_realiza_EVENTO_MEDICO WHERE id_procedimento_realizado = %d",
							eventRs.getInt("id_procedimento_realizado"));
			medicosRs = medicosSt.executeQuery(sql);
			usAux = jud.getUser(eventRs.getInt("id_usuario"));
			procedAux = jpd.getProcedure(eventRs.getInt("id_procedimento"));
			estabAux = jed.getEstabelecimentoMedico(Cnpj.getInstance(eventRs
					.getString("cnpj_estabelecimento")));
			if (procedAux.getTipo().equals("consulta")) {
				if (medicosRs.next())
					ret.add(new Consulta(eventRs
							.getInt("id_procedimento_realizado"), usAux,
							eventRs.getTimestamp("data_realizacao"), estabAux,
							jmd.getDoctor(medicosRs.getString("crm_medico")),
							procedAux));
				else
					throw new SQLException(
							"Médico não cadastrado na base de dados.");
			} else {
				medicosAux = new ArrayList<Medico>();
				while (medicosRs.next()) {
					medicosAux.add(jmd.getDoctor(medicosRs
							.getString("crm_medico")));
				}
				ret.add(new Cirurgia(eventRs
						.getInt("id_procedimento_realizado"), usAux, eventRs
						.getTimestamp("data_realizacao"), (Hospital) estabAux,
						medicosAux, procedAux));
			}
		}

		return ret;
	}
	
	@Override
	public boolean desvinculaMedico(EstabelecimentoMedico estab, Medico med) throws SQLException {
		String sql = String.format("DELETE FROM MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO WHERE crm_medico = '%s' AND cnpj_estabelecimento = '%s';", med.getCrm(), estab.getCnpj().getCnpj());
		Statement st = Conexao.getConexao().createStatement();
		return st.execute(sql);
	}
}