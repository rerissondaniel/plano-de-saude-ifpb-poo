package br.edu.planodesaude.servicos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Cirurgia;
import br.edu.planodesaude.dominio.Consulta;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Hospital;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Plano;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.servicos.dao.UsuarioDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Conexao;
import br.edu.planodesaude.util.Cpf;
import br.edu.planodesaude.util.Endereco;

public class JDBCUsuarioDAO implements UsuarioDAO {

	@Override
	public ArrayList<Usuario> getAll() throws SQLException {

		ArrayList<Usuario> list = new ArrayList<Usuario>();
		JDBCPlanoDAO jpld = new JDBCPlanoDAO();
		String query = "SELECT * FROM USUARIO";

		Statement userStatement = Conexao.getConexao().createStatement();
		ResultSet resultSet;

		resultSet = userStatement.executeQuery(query);
		while (resultSet.next()) {
			// Construção do Usuário;
			int cod = resultSet.getInt("id_usuario");
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");

			Cpf cpf = Cpf.getInstance(resultSet.getString("cpf"));

			Endereco endereco = new Endereco(
					resultSet.getString("rua"),// Construção do Endereço do
												// Usuário.
					resultSet.getString("numero"),
					resultSet.getString("bairro"),
					resultSet.getString("cidade"),
					resultSet.getString("referencia"),
					resultSet.getString("cep"), resultSet.getString("estado"));

			Date dataEnt = resultSet.getDate("data_entrada");
			boolean ativo = resultSet.getBoolean("ativo");

			Plano plano = jpld.getPlano(resultSet.getInt("id_plano"));

			list.add(new Usuario(cod, cpf, nome, telefone, endereco, ativo,
					dataEnt, plano));
		}
		if (resultSet != null)
			resultSet.close();
		if (userStatement != null)
			userStatement.close();
		return list;

	}

	@Override
	public Usuario getUser(int id) throws SQLException {
		/*
		 * Retorna o usuário com o id passado como parâmetro, caso este seja
		 * encontrado na base de dados. Caso contrário retorna null.
		 */
		JDBCPlanoDAO jpld = new JDBCPlanoDAO();
		Statement stmt = Conexao.getConexao().createStatement(), aux = Conexao
				.getConexao().createStatement();
		String sql = String.format(
				"SELECT * FROM USUARIO WHERE id_usuario = %d;", id);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			/* Início Endereço */
			Endereco endereco = new Endereco(rs.getString("rua"),
					rs.getString("numero"), rs.getString("bairro"),
					rs.getString("cidade"), rs.getString("referencia"),
					rs.getString("cep"), rs.getString("estado"));
			/* Final Endereço */
			/* Início plano */
			Plano plano = jpld.getPlano(rs.getInt("id_plano"));
			/* Final Plano */
			/* Início Usuário */
			Usuario ret = new Usuario(rs.getInt("id_usuario"),
					Cpf.getInstance(rs.getString("cpf")), rs.getString("nome"),
					rs.getString("telefone"), endereco, rs.getBoolean("ativo"),
					rs.getDate("data_entrada"), plano);
			/* Final Usuário */
			stmt.close();
			aux.close();
			return ret;
		}
		return null;
	}

	@Override
	public Usuario getUser(Cpf cpf) throws SQLException {
		/*
		 * Retorna o usuário com o cpf passado como parâmetro, caso este seja
		 * encontrado na base de dados. Caso contrário retorna null.
		 */
		JDBCPlanoDAO jpld = new JDBCPlanoDAO();
		Statement stmt = Conexao.getConexao().createStatement(), aux = Conexao
				.getConexao().createStatement();
		String sql = String.format("SELECT * FROM USUARIO WHERE cpf = '%s';",
				cpf.getCpf());
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			/* Início Endereço */
			Endereco endereco = new Endereco(rs.getString("rua"),
					rs.getString("numero"), rs.getString("bairro"),
					rs.getString("cidade"), rs.getString("referencia"),
					rs.getString("cep"), rs.getString("estado"));
			/* Final Endereço */

			Plano plano = jpld.getPlano(rs.getInt("id_plano"));
			/* Final Plano */
			/* Início Usuário */
			Usuario ret = new Usuario(rs.getInt("id_usuario"),
					Cpf.getInstance(rs.getString("cpf")), rs.getString("nome"),
					rs.getString("telefone"), endereco, rs.getBoolean("ativo"),
					rs.getDate("data_entrada"), plano);
			/* Final Usuário */
			stmt.close();
			aux.close();
			return ret;
		}
		return null;
	}

	@Override
	public int insert(Usuario usuario) throws SQLException {
		/*
		 * Insere o usuário e retorna o novo id deste na base de dados.Caso não
		 * seja possível inseri-lo retorna a constante que indica que o usuário
		 * não foi encontrado.
		 */
		String insertion = String
				.format("INSERT INTO USUARIO(cpf, nome, telefone, rua, numero, cidade, referencia, bairro, cep, estado, data_entrada, id_plano)"
						+ "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d);",
						usuario.getCpf().getCpf().toString(),
						usuario.getNome(), usuario.getTelefone(), usuario
								.getEndereco().getRua(), usuario.getEndereco()
								.getNumero(),
						usuario.getEndereco().getCidade(), usuario
								.getEndereco().getReferencia(), usuario
								.getEndereco().getBairro(), usuario
								.getEndereco().getCep(), usuario.getEndereco()
								.getEstado(), usuario.getDataEntrada()
								.toString(), usuario.getPlano().getCod());
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet generatedKeys;
		statement.execute(insertion, Statement.RETURN_GENERATED_KEYS);
		generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			int ret = generatedKeys.getInt(1);
			generatedKeys.close();
			statement.close();
			return ret;
		}
		
		return USER_NOT_INSERTED;
	}

	@Override
	public boolean update(Usuario usuario) throws SQLException {
		/*
		 * Simplesmente atualiza um usuario no banco. Usa o id desse usuário
		 * para encontrar seu registro na base.
		 */
		Statement statement = Conexao.getConexao().createStatement();
		String sql = String
				.format("UPDATE USUARIO "
						+ "SET "
						+ "nome = '%s', "
						+ "telefone = '%s',	 "
						+ "rua = '%s', "
						+ "numero = '%s', "
						+ "bairro = '%s', "
						+ "cidade = '%s', "
						+ "referencia = '%s'," 
						+ "cep = '%s', " 
						+ "estado = '%s', "
						+ "id_plano = %d, "
						+ "ativo = " + usuario.isAtivo()
						+ " WHERE cpf = '%s'",
						usuario.getNome(),
						usuario.getTelefone(), 
						usuario.getEndereco().getRua(), 
						usuario.getEndereco().getNumero(), 
						usuario.getEndereco().getBairro(), 
						usuario.getEndereco().getCidade(),
						usuario.getEndereco().getReferencia(), 
						usuario.getEndereco().getCep(),
						usuario.getEndereco().getEstado(), 
						usuario.getPlano().getCod(),
						usuario.getCpf().getCpf());

		return statement.execute(sql);
	}

	@Override
	public ArrayList<EventoMedico> getProceduresOnInterval(Usuario usuario,
			Date begin, Date end) throws SQLException {
		String sql = String.format("SELECT * FROM EVENTO_MEDICO event_med "
				+ "WHERE data_realizacao BETWEEN '%s' AND '%s' "
				+ "AND event_med.id_usuario = %d", begin.toString()
				+ " 00:00:00", end.toString() + " 23:59:59",
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
}