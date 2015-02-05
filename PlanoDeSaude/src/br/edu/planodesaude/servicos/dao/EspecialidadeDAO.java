package br.edu.planodesaude.servicos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Especialidade;

public interface EspecialidadeDAO{
	
	public final int SPECIALTY_NOT_FOUND = -1;
	
	public ArrayList<Especialidade> getAll() throws SQLException;
	
	public Especialidade getSpecialty(int id) throws SQLException;
	
	public int insert(Especialidade especialidade) throws SQLException;
	
	public boolean update(Especialidade especialidade) throws SQLException;
	
	public Especialidade getSpecialty(String nomeEsp) throws SQLException;
}
