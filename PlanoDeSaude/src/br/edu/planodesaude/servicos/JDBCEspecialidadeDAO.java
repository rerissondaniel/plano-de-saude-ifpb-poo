package br.edu.planodesaude.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.servicos.dao.EspecialidadeDAO;
import br.edu.planodesaude.util.Conexao;

public class JDBCEspecialidadeDAO implements EspecialidadeDAO {
	
	@Override
	public ArrayList<Especialidade> getAll() throws SQLException {
		
		ArrayList<Especialidade> list = new ArrayList<Especialidade>();
		
		String sql = "SELECT * FROM ESPECIALIDADE ORDER BY nome";

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id_especialidade");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");

			list.add(new Especialidade(id, nome, descricao));
		}

		return list;
	}
	
	@Override
	public Especialidade getSpecialty(String nomeEsp) throws SQLException {

		String sql = String.format("SELECT * FROM ESPECIALIDADE WHERE LOWER(nome) = '%s'", nomeEsp.toLowerCase());

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			int id_esp = resultSet.getInt("id_especialidade");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");

			return new Especialidade(id_esp, nome, descricao);
		}

		return null;
	}
	
	@Override
	public Especialidade getSpecialty(int id) throws SQLException {

		String sql = String.format("SELECT * FROM ESPECIALIDADE WHERE id_especialidade = %d", id);

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			int id_esp = resultSet.getInt("id_especialidade");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");

			return new Especialidade(id_esp, nome, descricao);
		}

		return null;
	}

	@Override
	public int insert(Especialidade especialidade) throws SQLException {

		String sql = String.format("INSERT INTO ESPECIALIDADE(nome, descricao) VALUES('%s', '%s')",
				especialidade.getNome(), especialidade.getDescricao());

		Statement statement = Conexao.getConexao().createStatement();
		statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

		ResultSet resultSet = statement.getGeneratedKeys();

		if (resultSet.next()) {
			return resultSet.getInt(1);
		}

		return SPECIALTY_NOT_FOUND;
	}

	@Override
	public boolean update(Especialidade especialidade) throws SQLException {

		String sql = String.format(
				"UPDATE ESPECIALIDADE SET nome = '%s', descricao = '%s' WHERE id_especialidade = %d",
				especialidade.getNome(), especialidade.getDescricao(), especialidade.getCodigo());

		Statement statement = Conexao.getConexao().createStatement();

		return statement.execute(sql);
	}

}
