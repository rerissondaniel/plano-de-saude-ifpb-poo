package br.edu.planodesaude.servicos.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.util.Cnpj;

public interface EventoMedicoDAO {
        
        public final int MEDICAL_EVENT_NOT_FOUND = -1;
        
        public int insert(EventoMedico eventoMedico) throws SQLException;
        
        public EventoMedico getById(int idProcedimentoRealizado) throws SQLException;
        
        public ArrayList<EventoMedico> getScheduledProcedures(Medico medico) throws SQLException;
        
        public ArrayList<EventoMedico> searchByDate(Date data) throws SQLException;

        public ArrayList<EventoMedico> searchByDoctor(Medico medico) throws SQLException;

        public ArrayList<EventoMedico> searchBySpecialty(Especialidade especialidade) throws SQLException;

        public ArrayList<EventoMedico> searchByPatient(Usuario usuario) throws SQLException;
        
        public ArrayList<EventoMedico> searchByEstablishment(Cnpj cnpj) throws SQLException;
}