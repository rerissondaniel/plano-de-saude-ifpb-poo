package br.edu.planodesaude.servicos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Procedimento;

public interface ProcedimentoDAO {

	public final int PROCEDURE_NOT_FOUND = -1;

	public ArrayList<Procedimento> getAll() throws SQLException;

	public int insert(Procedimento procedimento) throws SQLException;
	
	public Procedimento getProcedure(int id) throws SQLException;
	
	public Procedimento getProcedure(String proc) throws SQLException;
	
	public boolean update(Procedimento procedimento) throws SQLException;
	
}
