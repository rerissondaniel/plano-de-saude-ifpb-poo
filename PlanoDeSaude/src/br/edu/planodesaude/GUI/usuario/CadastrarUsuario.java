package br.edu.planodesaude.GUI.usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import br.edu.planodesaude.controle.Cadastradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.exceptions.InsertionException;
import br.edu.planodesaude.exceptions.SearchException;

public class CadastrarUsuario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
    public CadastrarUsuario() {
        
    	setTitle("Cadastrar Usuário");
    	setLocation((int)JDesktopPane.CENTER_ALIGNMENT, (int)JDesktopPane.CENTER_ALIGNMENT);
    	setClosable(true);
    	setVisible(true);
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
    	
        planoButtonGroup = new javax.swing.ButtonGroup();
        nomeJLabel = new javax.swing.JLabel();
        cpfJLabel = new javax.swing.JLabel();
        telefoneJLabel = new javax.swing.JLabel();
        enderecoJLabel = new javax.swing.JLabel();
        ruaJLabel = new javax.swing.JLabel();
        numeroJLabel = new javax.swing.JLabel();
        referenciaJLabel = new javax.swing.JLabel();
        bairroJLabel = new javax.swing.JLabel();
        cepJLabel = new javax.swing.JLabel();
        cidadeJLabel = new javax.swing.JLabel();
        estadoJLabel = new javax.swing.JLabel();
        planoJLabel = new javax.swing.JLabel();
        cadastrarJButton = new javax.swing.JButton();
        nomeJTextField = new javax.swing.JTextField();
        cpfJFormattedTextField = new javax.swing.JFormattedTextField();
        telefoneJFormattedTextField = new javax.swing.JFormattedTextField();
        ruaJTextField = new javax.swing.JTextField();
        numeroJTextField = new javax.swing.JTextField();
        referenciaJTextField = new javax.swing.JTextField();
        bairroJTextField = new javax.swing.JTextField();
        cepJFormattedTextField = new javax.swing.JFormattedTextField();
        cidadeJTextField = new javax.swing.JTextField();
        estadoJComboBox = new javax.swing.JComboBox();
        basicoJRadioButton = new javax.swing.JRadioButton();
        especialJRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nomeJLabel.setText("Nome");

        cpfJLabel.setText("CPF");

        telefoneJLabel.setText("Telefone");

        enderecoJLabel.setText("Endereço");

        ruaJLabel.setText("Rua");

        numeroJLabel.setText("Número");

        referenciaJLabel.setText("Referência");

        bairroJLabel.setText("Bairro");

        cepJLabel.setText("CEP");

        cidadeJLabel.setText("Cidade");

        estadoJLabel.setText("Estado");

        planoJLabel.setText("Plano");

        cadastrarJButton.setText("Cadastrar");
        cadastrarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarJButtonActionPerformed(evt);
            }
        });

        try {
            cpfJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            telefoneJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            cepJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        estadoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        planoButtonGroup.add(basicoJRadioButton);
        basicoJRadioButton.setText("Básico");

        planoButtonGroup.add(especialJRadioButton);
        especialJRadioButton.setText("Especial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enderecoJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeJLabel)
                                    .addComponent(cpfJLabel)
                                    .addComponent(telefoneJLabel)
                                    .addComponent(planoJLabel))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nomeJTextField)
                                    .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ruaJTextField)
                                    .addComponent(referenciaJTextField)
                                    .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bairroJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(basicoJRadioButton)
                                        .addGap(49, 49, 49)
                                        .addComponent(especialJRadioButton))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                                    .addComponent(cidadeJTextField)
                                    .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroJLabel)
                            .addComponent(ruaJLabel)
                            .addComponent(referenciaJLabel)
                            .addComponent(bairroJLabel)
                            .addComponent(cepJLabel)
                            .addComponent(cidadeJLabel)
                            .addComponent(estadoJLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(cadastrarJButton)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nomeJLabel)
                                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(cpfJLabel))
                            .addComponent(cpfJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(telefoneJLabel))
                    .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(enderecoJLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(ruaJLabel)
                                                                            .addComponent(ruaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(numeroJLabel))
                                                                    .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(referenciaJLabel))
                                                            .addComponent(referenciaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(bairroJLabel))
                                                    .addComponent(bairroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(cepJLabel))
                                            .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(cidadeJLabel))
                                    .addComponent(cidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(estadoJLabel))
                            .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(planoJLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(basicoJRadioButton)
                        .addComponent(especialJRadioButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(cadastrarJButton)
                .addContainerGap())
        );

        setBounds(424, 102, 510, 553);
    }

    private void cadastrarJButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if(nomeJTextField.getText().equals("")){
        	JOptionPane.showMessageDialog(null, "Campo nome obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }else 
        if(cpfJFormattedTextField.getText().equals("   .   .   -  ")){
        	JOptionPane.showMessageDialog(null, "Campo CPF obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
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
        }else 
        if(!basicoJRadioButton.isSelected() && !especialJRadioButton.isSelected()){
        	JOptionPane.showMessageDialog(null, "Seleção de plano obrigatória!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }else{
        	try{
        		String plano = basicoJRadioButton.isSelected()? "basico" : "especial";
	        	new Cadastradora().cadastrarUsuario(cpfJFormattedTextField.getText(), nomeJTextField.getText(), telefoneJFormattedTextField.getText(), 
	        			ruaJTextField.getText(), numeroJTextField.getText(), bairroJTextField.getText(), 
	        			cidadeJTextField.getText(), referenciaJTextField.getText(), cepJFormattedTextField.getText(),
	        			estadoJComboBox.getSelectedItem().toString(), plano);
	        	JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
	        	this.dispose();
	        }catch(InsertionException iex){
	        	JOptionPane.showMessageDialog(null, iex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private javax.swing.JLabel bairroJLabel;
    private javax.swing.JTextField bairroJTextField;
    private javax.swing.JRadioButton basicoJRadioButton;
    private javax.swing.JButton cadastrarJButton;
    private javax.swing.JFormattedTextField cepJFormattedTextField;
    private javax.swing.JLabel cepJLabel;
    private javax.swing.JLabel cidadeJLabel;
    private javax.swing.JTextField cidadeJTextField;
    private javax.swing.JFormattedTextField cpfJFormattedTextField;
    private javax.swing.JLabel cpfJLabel;
    private javax.swing.JLabel enderecoJLabel;
    private javax.swing.JRadioButton especialJRadioButton;
    private javax.swing.JComboBox estadoJComboBox;
    private javax.swing.JLabel estadoJLabel;
    private javax.swing.JLabel nomeJLabel;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JLabel numeroJLabel;
    private javax.swing.JTextField numeroJTextField;
    private javax.swing.ButtonGroup planoButtonGroup;
    private javax.swing.JLabel planoJLabel;
    private javax.swing.JLabel referenciaJLabel;
    private javax.swing.JTextField referenciaJTextField;
    private javax.swing.JLabel ruaJLabel;
    private javax.swing.JTextField ruaJTextField;
    private javax.swing.JFormattedTextField telefoneJFormattedTextField;
    private javax.swing.JLabel telefoneJLabel;
}
