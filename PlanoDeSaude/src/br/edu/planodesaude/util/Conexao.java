package br.edu.planodesaude.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

	private static Connection connection = null;
	private static boolean open = false;

	private Conexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/planoDeSaude", "root", "");
	}

	public static boolean openConnection() throws ClassNotFoundException, SQLException {
		if (!open) {
			new Conexao();
			setOpen(true);
			return true;
		}
		return false;
	}

	public static Connection getConexao() {
		if (open)
			return connection;
		return null;
	}

	public static boolean closeConnection() {
		try {
			connection.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean isOpen(){
		return open;
	}
	
	private static void setOpen(boolean aberto) {
		open = aberto;
	}
}
