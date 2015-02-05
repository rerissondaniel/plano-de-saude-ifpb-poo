package br.edu.planodesaude.servicos.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.util.Cnpj;

public interface EstabelecimentoMedicoDAO {

	public final int MEDICAL_ESTABLISHMENT_NOT_FOUND = -1;

	public ArrayList<EstabelecimentoMedico> getAll() throws SQLException;

	public boolean insert(EstabelecimentoMedico estabelecimentoMedico)
			throws SQLException;

	public boolean update(EstabelecimentoMedico estabelecimentoMedico)
			throws SQLException;

	public EstabelecimentoMedico getEstabelecimentoMedico(Cnpj cnpj)
			throws SQLException;

	public ArrayList<EventoMedico> getProceduresOnInterval(
			EstabelecimentoMedico estabelecimento, Date begin, Date end)
			throws SQLException;

	public boolean desvinculaMedico(EstabelecimentoMedico estab, Medico med)
			throws SQLException;
}
