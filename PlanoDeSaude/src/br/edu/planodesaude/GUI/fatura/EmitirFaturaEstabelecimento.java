package br.edu.planodesaude.GUI.fatura;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.EnumSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import br.edu.planodesaude.controle.Faturadora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.exceptions.InvoiceException;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.util.Formatadora;
import br.edu.planodesaude.util.GeradorPDF;
import br.edu.planodesaude.util.Mes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gustavo
 */
public class EmitirFaturaEstabelecimento extends javax.swing.JInternalFrame {

    /**
     * Creates new form EmitirFaturaEstabelecimento
     */
    public EmitirFaturaEstabelecimento() {
    	setTitle("Emitir Fatura para Estabelecimento");
    	setVisible(true);
    	setClosable(true);
    	setMaximizable(true);
        initComponents();
        initAnos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">      
    
    private void initAnos(){
    	DefaultComboBoxModel model = new DefaultComboBoxModel();
    	for(int ano = 1990; ano <= 2100; ano++){
    		model.addElement(ano);
    	}
    	anoJComboBox.setModel(model);
    }
    
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        cnpjJLabel = new javax.swing.JLabel();
        cnpjJFormattedTextField = new javax.swing.JFormattedTextField();
        anoJComboBox = new javax.swing.JComboBox();
        buscarJButton = new javax.swing.JButton();
        nomeJTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultadoJTextArea = new javax.swing.JTextArea();
        emitirFaturaJButton = new javax.swing.JButton();
        salvarJButton = new javax.swing.JButton();
        mesJComboBox = new javax.swing.JComboBox();
        mesJLabel = new javax.swing.JLabel();
        anoJLabel = new javax.swing.JLabel();
        nomeJLabel = new javax.swing.JLabel();
        
        resultadoJTextArea.setEditable(false);

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cnpjJLabel.setText("CNPJ");

        try {
            cnpjJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        buscarJButton.setText("Buscar");
        buscarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarJButtonActionPerformed(evt);
            }
        });

        resultadoJTextArea.setColumns(20);
        resultadoJTextArea.setRows(5);
        jScrollPane3.setViewportView(resultadoJTextArea);

        emitirFaturaJButton.setText("Emitir Fatura");
        emitirFaturaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirFaturaJButtonActionPerformed(evt);
            }
        });

        salvarJButton.setText("Salvar");
        salvarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarJButtonActionPerformed(evt);
            }
        });

        mesJComboBox.setModel(new javax.swing.DefaultComboBoxModel(EnumSet.range(Mes.JANEIRO, Mes.DEZEMBRO).toArray()));

        mesJLabel.setText("Mês");

        anoJLabel.setText("Ano");

        nomeJLabel.setText("Nome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salvarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cnpjJLabel)
                                    .addComponent(nomeJLabel))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buscarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(mesJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mesJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(anoJLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(anoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(emitirFaturaJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mesJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mesJLabel)
                        .addComponent(anoJLabel)
                        .addComponent(anoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscarJButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cnpjJLabel)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emitirFaturaJButton)
                    .addComponent(nomeJLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salvarJButton)
                .addContainerGap())
        );

        setBounds(270, 81, 812, 525);
    }// </editor-fold>                        

    private void buscarJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(cnpjJFormattedTextField.getText().equals(" .   .   /    -  ")){
    		JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}else{
    		try{
    			EstabelecimentoMedico estab = new Pesquisadora().pesquisarEstabelecimento(cnpjJFormattedTextField.getText());
    			nomeJTextField.setText(estab.getNome());
    			cnpjJFormattedTextField.setEditable(false);
    			nomeJTextField.setEditable(false);
    		}catch(SearchException sex){
    			JOptionPane.showMessageDialog(null, sex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }                                             

    private void emitirFaturaJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
      	if(cnpjJFormattedTextField.getText().equals("  .   .   /    -  ")){
    		JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}else{
    		try{
    			Faturadora fat = new Faturadora();
    			String data = String.format("%s/%2d", anoJComboBox.getSelectedItem().toString(), ((Mes)mesJComboBox.getSelectedItem()).getValor());
    			String result = fat.faturaMensalEstabelecimento(cnpjJFormattedTextField.getText(), data);
    			resultadoJTextArea.setText(result);
    			
    		}catch(InvoiceException iex){
    			JOptionPane.showMessageDialog(null, iex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }                                                   

    private void salvarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	if(!resultadoJTextArea.getText().equals("")){
    		File f = null;
    		int ans = JOptionPane.NO_OPTION;
    		try{
    			f = GeradorPDF.createDocument(resultadoJTextArea.getText());
    			ans = JOptionPane.showConfirmDialog(null,"Deseja abrir o arquivo?", "Ver ou não ver", JOptionPane.INFORMATION_MESSAGE);
    		}catch(FileSystemException fsex){
    			JOptionPane.showMessageDialog(null, fsex.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
    		}
    		if(ans == JOptionPane.YES_OPTION){
    			try {
					Desktop.getDesktop().open(f);
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, ioe.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
    		}
    	}else{
    		if(cnpjJFormattedTextField.getText().equals("  .   .   /    -  "))
    			JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Alerta!", JOptionPane.WARNING_MESSAGE);
    		else
    			JOptionPane.showMessageDialog(null, "Clique em emitir fatura primeiro", "Alerta!", JOptionPane.WARNING_MESSAGE);
    	}
    }                                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmitirFaturaEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmitirFaturaEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmitirFaturaEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmitirFaturaEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmitirFaturaEstabelecimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox anoJComboBox;
    private javax.swing.JLabel anoJLabel;
    private javax.swing.JButton buscarJButton;
    private javax.swing.JFormattedTextField cnpjJFormattedTextField;
    private javax.swing.JLabel cnpjJLabel;
    private javax.swing.JButton emitirFaturaJButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox mesJComboBox;
    private javax.swing.JLabel mesJLabel;
    private javax.swing.JLabel nomeJLabel;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JTextArea resultadoJTextArea;
    private javax.swing.JButton salvarJButton;
    // End of variables declaration                   
}
