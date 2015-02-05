package br.edu.planodesaude.GUI.usuario;

import javax.swing.JOptionPane;

import br.edu.planodesaude.controle.Alteradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.exceptions.UpdateException;

public class AlterarStatusUsuario extends javax.swing.JInternalFrame {
        
    public AlterarStatusUsuario() {
    	setClosable(true);
    	setVisible(true);
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Desabilitar Usuário");
        jButton1 = new javax.swing.JButton();
        cpfJLabel = new javax.swing.JLabel();
        nomeJLabel = new javax.swing.JLabel();
        nomeJTextField = new javax.swing.JTextField();
        situacaoJLabel = new javax.swing.JLabel();
        cpfJFormattedTextField = new javax.swing.JFormattedTextField();
        buscarJButton = new javax.swing.JButton();
        situacaojToggleButton = new javax.swing.JToggleButton();
        salvarjButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cpfJLabel.setText("CPF");

        nomeJLabel.setText("Nome");

        situacaoJLabel.setText("Situação");

        try {
            cpfJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        buscarJButton.setText("Buscar");
        buscarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarJButtonActionPerformed(evt);
            }
        });

        situacaojToggleButton.setText("       ");
        situacaojToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                situacaojToggleButtonActionPerformed(evt);
            }
        });

        salvarjButton.setText("Salvar");
        salvarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(salvarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeJLabel)
                            .addComponent(situacaoJLabel)
                            .addComponent(cpfJLabel))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buscarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(situacaojToggleButton))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cpfJLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscarJButton)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(situacaoJLabel)
                    .addComponent(situacaojToggleButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(salvarjButton)
                .addContainerGap())
        );

        setBounds(455, 214, 458, 259);
    }
   
        private void buscarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
            if(cpfJFormattedTextField.getText().equals("   .   .   -  ")){
            	JOptionPane.showMessageDialog(null, "Preencha o campo CPF!", "Alerta!", JOptionPane.WARNING_MESSAGE);
            }else{
            	try{
            		us = new Pesquisadora().pesquisarUsuario(cpfJFormattedTextField.getText());
            		cpfJFormattedTextField.setEditable(false);
            		nomeJTextField.setText(us.getNome());
            		nomeJTextField.setEditable(false);
            		if(us.isAtivo()){
            			situacaojToggleButton.setText("Ativo");
            		}else{
            			situacaojToggleButton.setText("Inativo");
            			situacaojToggleButton.setSelected(true);
            		}
            	}catch(SearchException sex){
            		JOptionPane.showMessageDialog(null, sex.getMessage(), "Alerta!", JOptionPane.WARNING_MESSAGE);
            	}            	
            }
        }                                             
        
        private void situacaojToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {
                us.setAtivo(!us.isAtivo());
                situacaojToggleButton.setText(us.isAtivo() ? "Ativo" : "Inativo");
        }
                                            
        
        private void salvarjButtonActionPerformed(java.awt.event.ActionEvent evt) {
        	if(cpfJFormattedTextField.getText().equals("   .   .   -  ")){
            	JOptionPane.showMessageDialog(null, "Preencha o campo CPF!", "Alerta!", JOptionPane.WARNING_MESSAGE);
            }else{    
            	try{
                        new Alteradora().alterarUsuario(us);
                        JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                }catch(UpdateException uex){
                        JOptionPane.showMessageDialog(null, uex.getMessage(), "Alerta!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlterarStatusUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarStatusUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarStatusUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarStatusUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AlterarStatusUsuario().setVisible(true);
            }
        });
    }
  
    private javax.swing.JButton buscarJButton;
    private javax.swing.JFormattedTextField cpfJFormattedTextField;
    private javax.swing.JLabel cpfJLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel nomeJLabel;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JButton salvarjButton;
    private javax.swing.JLabel situacaoJLabel;
    private javax.swing.JToggleButton situacaojToggleButton;
    private Usuario us;
}