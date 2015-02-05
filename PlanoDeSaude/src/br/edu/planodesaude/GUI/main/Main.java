package br.edu.planodesaude.GUI.main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.planodesaude.GUI.procedimento.MarcarConsulta;
import br.edu.planodesaude.util.Conexao;

public final class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MarcarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarcarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarcarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarcarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		try {
			Conexao.openConnection();
		} catch (ClassNotFoundException cnfex) {
			JOptionPane
					.showMessageDialog(
							null,
							"Driver do Banco de Dados não Encontrado.\n    Funcionalidade do Sistema reduzida.\n  Tente novamente ou chame o suporte.", "Erro", JOptionPane.ERROR_MESSAGE);
			
			cnfex.printStackTrace();
		} catch (SQLException sqlex) {
			JOptionPane
			.showMessageDialog(
					null,
					"Erro ao tentar conectar-se com o Banco de Dados.\n        Funcionalidade do Sistema reduzida.\n      Tente novamente ou chame o suporte.", "Erro", JOptionPane.ERROR_MESSAGE);
			sqlex.printStackTrace();
		}
		new TelaPrincipal().main(args);
		// Conexao.closeConnection();
	}
}