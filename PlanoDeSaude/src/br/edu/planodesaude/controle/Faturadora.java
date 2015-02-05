package br.edu.planodesaude.controle;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.EventoMedico;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.InvoiceException;
import br.edu.planodesaude.servicos.JDBCEstabelecimentoMedicoDAO;
import br.edu.planodesaude.servicos.JDBCUsuarioDAO;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Cpf;

public class Faturadora {
		
	public String faturaMensalEstabelecimento(String cnpjs, String anoEmes) throws InvoiceException{
		Cnpj cnpj;
		try{
			cnpj = Cnpj.getInstance(cnpjs);
		}catch(IllegalArgumentException ilaex){
			throw new InvoiceException(ilaex.getMessage());
		}
		String strBeg, strEnd, ret;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		strBeg = anoEmes + "/01";
		strEnd = anoEmes + "/31";
		Date begin, end;
		try{
			begin = new Date(sdf.parse(strBeg).getTime());
			end = new Date(sdf.parse(strEnd).getTime());
		}catch(ParseException pe){
			pe.printStackTrace();
			throw new InvoiceException("Formato de data inválido!");
		}
		JDBCEstabelecimentoMedicoDAO jemd = new JDBCEstabelecimentoMedicoDAO();
		EstabelecimentoMedico estabelecimento;
		ArrayList<EventoMedico> eventos;
		try{
		    estabelecimento = jemd.getEstabelecimentoMedico(cnpj);
			eventos = jemd.getProceduresOnInterval(estabelecimento, begin, end);
		}catch(SQLException ex){
			throw new InvoiceException("Falha na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		ret = estabelecimento.geraFatura(eventos);
		return ret;
	}
	
	public String faturaMensalUsuario(String cpfs, String anoEmes) throws InvoiceException {
		Cpf cpf;
		try{
			cpf = Cpf.getInstance(cpfs);
		}catch(IllegalArgumentException ilaex){
			throw new InvoiceException(ilaex.getMessage());
		}
		String strBeg, strEnd, ret;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		strBeg = anoEmes + "/01";
		strEnd = anoEmes + "/31";
		Date begin, end;
		try{
			begin = new Date(sdf.parse(strBeg).getTime());
			end = new Date(sdf.parse(strEnd).getTime());
		}catch(ParseException pe){
			pe.printStackTrace();
			throw new InvoiceException("Formato de data inválido!");
		}
		JDBCUsuarioDAO jud = new JDBCUsuarioDAO();
		Usuario usuario;
		ArrayList<EventoMedico> eventos;
		try{
		    usuario = jud.getUser(cpf);
			eventos = jud.getProceduresOnInterval(usuario, begin, end);
		}catch(SQLException ex){
			throw new InvoiceException("Falha na conexão com o banco de dados, chame o suporte ou tente novamente.");
		}
		ret = usuario.getPlano().geraFatura(usuario, eventos);
		return ret;
	}
}
