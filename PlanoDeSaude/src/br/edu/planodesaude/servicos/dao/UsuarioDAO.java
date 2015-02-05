package br.edu.planodesaude.servicos.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.util.Cpf;

public interface UsuarioDAO {

	public final int USER_NOT_INSERTED = -1;

	public ArrayList<Usuario> getAll() throws SQLException;

	public Usuario getUser(int id) throws SQLException;

	public int insert(Usuario usuario) throws SQLException;

	public boolean update(Usuario usuario) throws SQLException;

	public ArrayList<EventoMedico> getProceduresOnInterval(Usuario usuario,
			Date begin, Date end) throws SQLException;

	public Usuario getUser(Cpf cpf) throws SQLException;
}
