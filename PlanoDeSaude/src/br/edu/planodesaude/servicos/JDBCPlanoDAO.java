package br.edu.planodesaude.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.planodesaude.dominio.Plano;
import br.edu.planodesaude.dominio.PlanoBasico;
import br.edu.planodesaude.dominio.PlanoEspecial;
import br.edu.planodesaude.servicos.dao.PlanoDAO;
import br.edu.planodesaude.util.Conexao;

public class JDBCPlanoDAO implements PlanoDAO {
	@Override
	public Plano getPlano(int id) throws SQLException {
		Statement st = Conexao.getConexao().createStatement();

		ResultSet planoSet = st.executeQuery(String.format(
				"SELECT * FROM PLANO WHERE id_plano = %d", id));
		planoSet.next();
		if (planoSet.getString("descricao").equals("basico")) {
			return new PlanoBasico(id, planoSet.getString("descricao"));
		}
		if (planoSet.getString("descricao").equals("especial")) {
			return new PlanoEspecial(id, planoSet.getString("descricao"));
		}
		return null;
	}

}
