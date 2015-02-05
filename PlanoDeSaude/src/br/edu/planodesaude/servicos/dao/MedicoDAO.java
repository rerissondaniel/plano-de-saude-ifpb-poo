package br.edu.planodesaude.servicos.dao;

import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.EventoMedico;

public interface MedicoDAO {
	
	public final int MEDICO_NOT_FOUND = -1;
	
	public ArrayList<Medico> getAll() throws SQLException;
	
	public Medico getDoctor(String crm) throws SQLException;
	
	public String insert(Medico medico) throws SQLException;

	public boolean update(Medico medico) throws SQLException;
		
	public ArrayList<EventoMedico> getMedicalEventOnInterval(Medico medico, Date begin, Date end)
			throws SQLException;
}
