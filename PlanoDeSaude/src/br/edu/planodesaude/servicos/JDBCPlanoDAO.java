package br.edu.planodesaude.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.planodesaude.dominio.Plano;
import br.edu.planodesaude.servicos.dao.PlanoDAO;
import br.edu.planodesaude.util.Conexao;

public class JDBCPlanoDAO implements PlanoDAO {
	@Override
	public Plano getPlano(int id) throws SQLException {
		Statement st = Conexao.getConexao().createStatement();

		ResultSet planoSet = st.executeQuery(String.format(
				"SELECT * FROM PLANO WHERE id_plano = %d", id));
		planoSet.next();
		try {
			String tipoPlano = planoSet.getString("descricao");
			tipoPlano = "br.edu.planodesaude.dominio.Plano" + tipoPlano.substring(0, 1).toUpperCase() + tipoPlano.substring(1, tipoPlano.length());
			Plano p = (Plano) Class.forName(tipoPlano).getConstructor(int.class, String.class).newInstance(id, tipoPlano);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
