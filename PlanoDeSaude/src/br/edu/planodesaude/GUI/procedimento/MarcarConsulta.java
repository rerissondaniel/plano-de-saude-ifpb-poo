package br.edu.planodesaude.GUI.procedimento;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import br.edu.planodesaude.GUI.main.Exibir;
import br.edu.planodesaude.controle.Cadastradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.dominio.Procedimento;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.InsertionException;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.util.Conexao;
import br.edu.planodesaude.util.Formatadora;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rerissondaniel
 */
public class MarcarConsulta extends javax.swing.JInternalFrame {

    /**
     * Creates new form MarcarConsulta
     */
    public MarcarConsulta() {
    	setTitle("Marcar Consulta");
    	setVisible(true);
    	setClosable(true);
        initComponents();
        initEstab();
        initMed();
        initProc();
    }
    
    public void initEstab(){
		
		Object[] items = null;
		
		try{
			items = new Pesquisadora().getEstabelecimentos().toArray();
		}catch(SearchException sex){
			JOptionPane.showMessageDialog(null, sex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel(items);
				
		estabelecimentoJComboBox.setModel(model);
	}
	
	public void initMed(){
		try{
			medicoJComboBox.setModel(new DefaultComboBoxModel(new Pesquisadora().getMedicos().toArray()));
		} catch(SearchException sex){
			JOptionPane.showMessageDialog(null, sex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void initProc(){
		ArrayList<Procedimento> procs = new ArrayList<Procedimento>();
		
		try{
			procs = new Pesquisadora().getConsultas();
		}catch(SearchException sex){
			JOptionPane.showMessageDialog(null, sex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel(procs.toArray());
		
		consultaJComboBox.setModel(model);
	}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        estabelecimentoJComboBox = new javax.swing.JComboBox();
        medicoJComboBox = new javax.swing.JComboBox();
        consultaJComboBox = new javax.swing.JComboBox();
        detalhesJButton = new javax.swing.JButton();
        nomeJTextField = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        buscarJButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        horarioJFormattedTextField = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CPF");

        jLabel2.setText("Nome");

        jLabel3.setText("Estabelecimento");

        jLabel4.setText("Médico");

        jLabel5.setText("Consulta");

        detalhesJButton.setText("Detalhes");
        detalhesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesJButtonActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        buscarJButton.setText("Buscar");
        buscarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarJButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Horário");

        try {
            horarioJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/#### - ##h##min")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton3.setText("Marcar Consulta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jFormattedTextField1)
                                    .addGap(18, 18, 18)
                                    .addComponent(buscarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(estabelecimentoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(medicoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(consultaJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(detalhesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addComponent(nomeJTextField))
                            .addComponent(horarioJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buscarJButton)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(estabelecimentoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(medicoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(consultaJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(detalhesJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(horarioJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jButton3)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setBounds(370, 135, 625, 439);
    }// </editor-fold>                        
    
    Usuario us;
    private void buscarJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	System.out.println(getBounds().toString());
    	if(jFormattedTextField1.getText().equals("   .   .   -  ")){
    		JOptionPane.showMessageDialog(null, "Informe o CPF", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}else{
    		try{
    			us = new Pesquisadora().pesquisarUsuario(jFormattedTextField1.getText());
    			nomeJTextField.setText(us.getNome());
    			jFormattedTextField1.setEditable(false);
    			nomeJTextField.setEditable(false);
    		}catch(SearchException sex){
    			JOptionPane.showMessageDialog(null, sex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }                                            

    private void detalhesJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	Procedimento proced = (Procedimento)consultaJComboBox.getSelectedItem();
    	String str = String.format("Nome: %s\nPreço: %.2f\nEspecialidade: %s\n", proced.getNome(), proced.getPreco(), proced.getEspecialidade().getNome());
        new Exibir(Formatadora.format(str, 62)).setVisible(true);
    }                                              

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(jFormattedTextField1.getText().equals("   .   .   -  ")){
    		JOptionPane.showMessageDialog(null, "Informe o CPF", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}else if(horarioJFormattedTextField.getText().equals("  /  /     -   h  min")){
    		JOptionPane.showMessageDialog(null, "Insira um horário!", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}else{
    		try{
    			Procedimento proc = (Procedimento)consultaJComboBox.getSelectedItem();
    			EstabelecimentoMedico estab = (EstabelecimentoMedico) estabelecimentoJComboBox.getSelectedItem();
    			String str = horarioJFormattedTextField.getText().replace('h', ':');
    			str = str.replaceAll("min", "");
    			Timestamp data = new Timestamp((new SimpleDateFormat("dd/MM/yyyy - hh:mm").parse(str).getTime()));
    			new Cadastradora().marcarConsulta(us, estab, (Medico)medicoJComboBox.getSelectedItem(), proc, data);
    			JOptionPane.showMessageDialog(null, "Consulta marcada com sucesso!", "Alerta!", JOptionPane.INFORMATION_MESSAGE);
    			dispose();
    		}catch(InsertionException iex){
    			JOptionPane.showMessageDialog(null, iex.getMessage(), "Alerta!", JOptionPane.WARNING_MESSAGE);
    		}catch(ParseException pex){
    			JOptionPane.showMessageDialog(null, "Insira um horário válido!", "Alerta!", JOptionPane.WARNING_MESSAGE);
    		}
    	}

    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarcarConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton buscarJButton;
    private javax.swing.JComboBox consultaJComboBox;
    private javax.swing.JButton detalhesJButton;
    private javax.swing.JComboBox estabelecimentoJComboBox;
    private javax.swing.JFormattedTextField horarioJFormattedTextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox medicoJComboBox;
    private javax.swing.JTextField nomeJTextField;
    // End of variables declaration                   
}
