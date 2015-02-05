package br.edu.planodesaude.servicos.dao;

import java.sql.SQLException;

import br.edu.planodesaude.dominio.Plano;

public interface PlanoDAO {
	public Plano getPlano(int id) throws SQLException ;
}
