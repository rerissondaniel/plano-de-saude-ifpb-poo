package br.edu.planodesaude.GUI.usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import br.edu.planodesaude.controle.Alteradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.Usuario;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.exceptions.UpdateException;
import br.edu.planodesaude.util.Endereco;

public class AtualizarUsuario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
    public AtualizarUsuario() {
    	
    	setClosable(true);
    	setVisible(true);
    	setTitle("Alterar Usuário");
    	initComponents();
        Object[] items = {"Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", 
                          "Ceará", "Distrito Federal", "Espírito Santo", 
                          "Goiás", "Maranhão", "Mato Grosso", 
                          "Mato Grosso do Sul", "Minas Gerais", "Pará", 
                          "Paraíba", "Paraná", "Pernambuco", "Piauí", 
                          "Rio de Janeiro", "Rio Grande do Norte", 
                          "Rio Grande do Sul", "Rondônia", "Roraima", 
                          "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"};
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(items);
        estadoJComboBox.setModel(model);
    }

    private void initComponents() {
    	this.setTitle("Atualizar Usuário");
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        buscarJButton = new javax.swing.JButton();
        cpfJFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        atualizarJButton = new javax.swing.JButton();
        nomeJTextField = new javax.swing.JTextField();
        ruaJTextField = new javax.swing.JTextField();
        numeroJTextField = new javax.swing.JTextField();
        referenciaJTextField = new javax.swing.JTextField();
        bairroJTextField = new javax.swing.JTextField();
        cidadeJTextField = new javax.swing.JTextField();
        cepJFormattedTextField = new javax.swing.JFormattedTextField();
        estadoJComboBox = new javax.swing.JComboBox();
        telefoneJFormattedTextField = new javax.swing.JFormattedTextField();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CPF");

        buscarJButton.setText("Buscar");
        buscarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarJButtonActionPerformed(evt);
            }
        });

        try {
            cpfJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("Nome");

        jLabel3.setText("Telefone");

        jLabel4.setText("Endereço");

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel8.setText("Referência");

        jLabel9.setText("Bairro");

        jLabel10.setText("CEP");

        jLabel11.setText("Cidade");

        jLabel12.setText("Estado");

        atualizarJButton.setText("Atualizar Dados");
        atualizarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarJButtonActionPerformed(evt);
            }
        });

        try {
            cepJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        

        estadoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
       

        try {
            telefoneJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bairroJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                            .addComponent(referenciaJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                            .addComponent(cidadeJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                            .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                                    .addComponent(nomeJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(ruaJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(numeroJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(atualizarJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarJButton))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(ruaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(referenciaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(bairroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(cidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(81, 81, 81)
                .addComponent(atualizarJButton)
                .addGap(40, 40, 40))
        );

        setBounds(419, 98, 510, 553);
    }

	private Usuario usuario;
	private void buscarJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			usuario = new Pesquisadora().pesquisarUsuario(cpfJFormattedTextField.getText());
			nomeJTextField.setText(usuario.getNome());
			telefoneJFormattedTextField.setText(usuario.getTelefone());
			referenciaJTextField.setText(usuario.getEndereco().getReferencia());
			bairroJTextField.setText(usuario.getEndereco().getBairro());
			ruaJTextField.setText(usuario.getEndereco().getRua());
			numeroJTextField.setText(usuario.getEndereco().getNumero());
			cepJFormattedTextField.setText(usuario.getEndereco().getCep());
			cidadeJTextField.setText(usuario.getEndereco().getCidade());
			estadoJComboBox.setSelectedItem(usuario.getEndereco().getEstado());
			cpfJFormattedTextField.setEditable(false);
		}catch(SearchException sex){
			JOptionPane.showMessageDialog(null, sex.getMessage(), "Alerta!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void atualizarJButtonActionPerformed(java.awt.event.ActionEvent evt) {

		 if(nomeJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo nome obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else  
	        if(telefoneJFormattedTextField.getText().equals("(  )     -    ")){
	        	JOptionPane.showMessageDialog(null, "Campo telefone obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(ruaJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo rua obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(numeroJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo numero obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(referenciaJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo referência obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(cepJFormattedTextField.getText().equals("     -   ")){
	        	JOptionPane.showMessageDialog(null, "Campo CEP obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(bairroJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo bairro obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(cidadeJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo cidade obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else{
	        	try{
	        		Endereco endereco = new Endereco(
		        			ruaJTextField.getText(), 
		        			numeroJTextField.getText(), 
		        			bairroJTextField.getText(), 
		        			cidadeJTextField.getText(), 
		        			referenciaJTextField.getText(), 
		        			cepJFormattedTextField.getText(), 
		        			estadoJComboBox.getSelectedItem().toString());
	        		usuario.setNome(nomeJTextField.getText());
	        		usuario.setTelefone(telefoneJFormattedTextField.getText());
	        		usuario.setEndereco(endereco);
	   
	        		new Alteradora().alterarUsuario(usuario);
	        		JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
	        		this.dispose();
	        	}catch(UpdateException upex){
	        		JOptionPane.showMessageDialog(null, upex.getMessage(), "Erro!", JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(AtualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AtualizarUsuario().setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton atualizarJButton;
    private javax.swing.JButton buscarJButton;
    private javax.swing.JComboBox estadoJComboBox;
    private javax.swing.JFormattedTextField cpfJFormattedTextField;
    private javax.swing.JFormattedTextField cepJFormattedTextField;
    private javax.swing.JFormattedTextField telefoneJFormattedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JTextField ruaJTextField;
    private javax.swing.JTextField numeroJTextField;
    private javax.swing.JTextField referenciaJTextField;
    private javax.swing.JTextField bairroJTextField;
    private javax.swing.JTextField cidadeJTextField;
}
