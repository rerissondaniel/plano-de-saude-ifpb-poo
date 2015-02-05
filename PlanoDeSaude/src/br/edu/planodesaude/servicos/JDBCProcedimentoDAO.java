package br.edu.planodesaude.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.servicos.dao.ProcedimentoDAO;
import br.edu.planodesaude.util.Conexao;

public class JDBCProcedimentoDAO implements ProcedimentoDAO {
	
	@Override
	public ArrayList<Procedimento> getAll() throws SQLException {

		ArrayList<Procedimento> list = new ArrayList<Procedimento>();

		String sql = "SELECT * FROM PROCEDIMENTO ORDER BY nome";

		JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int codigo = resultSet.getInt("id_procedimento");
			float preco = resultSet.getFloat("preco");
			String nome = resultSet.getString("nome");
			String tipo = resultSet.getString("tipo_procedimento");

			Especialidade especialidade = jed.getSpecialty(resultSet.getInt("id_especialidade"));

			list.add(new Procedimento(codigo, preco, nome, tipo, especialidade));
		}

		return list;
	}

	@Override
	public int insert(Procedimento procedimento) throws SQLException {

		String sql = String
				.format(Locale.US,
						"INSERT INTO PROCEDIMENTO(preco, nome, tipo_procedimento, id_especialidade) VALUES(%.2f, '%s', '%s', %d)",
						procedimento.getPreco(), procedimento.getNome(), procedimento.getTipo(), procedimento
								.getEspecialidade().getCodigo(), procedimento.getEspecialidade().getCodigo());

		Statement statement = Conexao.getConexao().createStatement();

		statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

		ResultSet resultSet = statement.getGeneratedKeys();

		if (resultSet.next()) {
			return resultSet.getInt(1);
		}

		return PROCEDURE_NOT_FOUND;
	}
	
	@Override
	public Procedimento getProcedure(String nomeProcedimento) throws SQLException {

		String sql = String.format("SELECT * FROM PROCEDIMENTO WHERE LOWER(nome) = '%s'", nomeProcedimento.toLowerCase());

		Statement statement = Conexao.getConexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			int id_proc = resultSet.getInt("id_procedimento");
			String nome = resultSet.getString("nome");
			String tipo = resultSet.getString("tipo_procedimento");
			float preco = resultSet.getFloat("preco");
			JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();
			return new Procedimento(id_proc, preco, nome, tipo, jed.getSpecialty(resultSet.getInt("id_especialidade")));
		}

		return null;
	}
	
	@Override
	public Procedimento getProcedure(int id) throws SQLException {
		String sql = String.format("SELECT * FROM PROCEDIMENTO WHERE id_procedimento = %d", id);

		Statement st = Conexao.getConexao().createStatement();

		ResultSet resultSet = st.executeQuery(sql);
		if (resultSet.next()) {
			int id_proc = resultSet.getInt("id_procedimento");
			String nome = resultSet.getString("nome");
			String tipo = resultSet.getString("tipo_procedimento");
			float preco = resultSet.getFloat("preco");
			JDBCEspecialidadeDAO jed = new JDBCEspecialidadeDAO();
			return new Procedimento(id_proc, preco, nome, tipo, jed.getSpecialty(resultSet.getInt("id_especialidade")));
		}
		return null;
	}

	@Override
	public boolean update(Procedimento procedimento) throws SQLException {
		String sql = String
				.format(Locale.US,
						"UPDATE PROCEDIMENTO SET preco = %.2f, nome = '%s', tipo_procedimento = '%s', id_especialidade = %d WHERE id_procedimento = %d",
						procedimento.getPreco(), procedimento.getNome(), procedimento.getTipo(), procedimento
								.getEspecialidade().getCodigo(), procedimento.getCodigo());

		Statement statement = Conexao.getConexao().createStatement();

		return statement.execute(sql);
	}
}
