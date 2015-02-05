package br.edu.planodesaude.GUI.estabelecimento;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import br.edu.planodesaude.GUI.main.Exibir;
import br.edu.planodesaude.GUI.medico.CadastrarEspecialidade;
import br.edu.planodesaude.GUI.medico.CadastrarMedico;
import br.edu.planodesaude.controle.Cadastradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.exceptions.DoctorNotFoundException;
import br.edu.planodesaude.exceptions.InsertionException;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.util.Cnpj;
import br.edu.planodesaude.util.Conexao;
import br.edu.planodesaude.util.Formatadora;

public class CadastrarEstabelecimento extends javax.swing.JInternalFrame {
	
	private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	private static DefaultListModel modelEsp = new DefaultListModel();
	private static DefaultListModel modelMed = new DefaultListModel();
	
	private static void initJListEsp(){
		modelEsp.clear();
		
		try {
			ArrayList<Especialidade> esp = new Pesquisadora().getEspecialidades();
			
			for(Especialidade especialidade : esp){
				modelEsp.addElement(especialidade);
			}
			
		} catch (SearchException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		}
        
        especialidadesJList.setModel(modelEsp);
	}
	
	private static void initJListMed(){		
		modelMed.clear();
		
		try {
			ArrayList<Medico> med = new Pesquisadora().getMedicos();
			
			for(Medico medico : med){
				modelMed.addElement(medico);
			}
			
		} catch (SearchException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		}
        
        medicosJList.setModel(modelMed);
	}
	
    public CadastrarEstabelecimento() {
    	
    	setLocation((int)JDesktopPane.CENTER_ALIGNMENT, (int)JDesktopPane.CENTER_ALIGNMENT);
    	setClosable(true);
    	setVisible(true);
    	setTitle("Cadastrar Estabelecimento");
    	
    	initComponents();
    	initJListEsp();
    	initJListMed();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nomeJTextField = new javax.swing.JTextField();
        cnpjJFormattedTextField = new javax.swing.JFormattedTextField();
        telefoneJFormattedTextField = new javax.swing.JFormattedTextField();
        ruaJTextField = new javax.swing.JTextField();
        numeroJTextField = new javax.swing.JTextField();
        referenciaJTextField = new javax.swing.JTextField();
        bairroJTextField = new javax.swing.JTextField();
        cepJFormattedTextField = new javax.swing.JFormattedTextField();
        cidadeJTextField = new javax.swing.JTextField();
        estadoJComboBox = new javax.swing.JComboBox();
        hospitalJRadioButton = new javax.swing.JRadioButton();
        consultorioJRadioButton = new javax.swing.JRadioButton();
        especialidadeJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        especialidadesJList = new javax.swing.JList();
        visualizarEspJButton = new javax.swing.JButton();
        novaEspJButton = new javax.swing.JButton();
        detalhesJButton = new javax.swing.JButton();
        vincularEspJButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        medicosJList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        detalhesJTextArea = new javax.swing.JTextArea();
        vincularMedJButton = new javax.swing.JButton();
        vizualizarMedJButton = new javax.swing.JButton();
        cadastrarJButton = new javax.swing.JButton();
        novoMedJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Nome");

        jLabel2.setText("CNPJ");

        jLabel3.setText("Telefone");

        jLabel4.setText("Endereço");

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel7.setText("Referência");

        jLabel8.setText("Bairro");

        jLabel9.setText("CEP");

        jLabel10.setText("Cidade");

        jLabel11.setText("Estado");

        jLabel12.setText("Tipo");

        try {
            cnpjJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
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

        buttonGroup1.add(hospitalJRadioButton);
        hospitalJRadioButton.setText("Hospital");

        buttonGroup1.add(consultorioJRadioButton);
        consultorioJRadioButton.setText("Consultório");

        especialidadeJLabel.setText("Especialidade(s)");

        especialidadesJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(especialidadesJList);

        visualizarEspJButton.setText("Visualizar Especialidades Vinculadas");
        visualizarEspJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarEspJButtonActionPerformed(evt);
            }
        });

        novaEspJButton.setText("Nova Especialidade");
        novaEspJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novaEspJButtonActionPerformed(evt);
            }
        });

        detalhesJButton.setText("Detalhes");
        detalhesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesJButtonActionPerformed(evt);
            }
        });

        vincularEspJButton.setText("Vincular Especialidade");
        vincularEspJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vincularEspJButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Medico(s) Responsável(is)");

        medicosJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(medicosJList);

        detalhesJTextArea.setColumns(20);
        detalhesJTextArea.setRows(5);
        jScrollPane4.setViewportView(detalhesJTextArea);

        vincularMedJButton.setText("Vincular Médico");
        vincularMedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vincularMedJButtonActionPerformed(evt);
            }
        });

        vizualizarMedJButton.setText("Visualizar Médicos Vinculados");
        vizualizarMedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vizualizarMedJButtonActionPerformed(evt);
            }
        });

        cadastrarJButton.setText("Cadastrar");
        cadastrarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarJButtonActionPerformed(evt);
            }
        });

        novoMedJButton.setText("Novo Médico");
        novoMedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoMedJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)))
                            .addComponent(jLabel12))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hospitalJRadioButton)
                                .addGap(31, 31, 31)
                                .addComponent(consultorioJRadioButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ruaJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                .addComponent(referenciaJTextField)
                                .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bairroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(detalhesJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vincularEspJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(novaEspJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(visualizarEspJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vizualizarMedJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vincularMedJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(novoMedJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(especialidadeJLabel)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cadastrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(especialidadeJLabel)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vincularEspJButton)
                            .addComponent(detalhesJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(telefoneJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(ruaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novaEspJButton)
                    .addComponent(visualizarEspJButton)
                    .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(referenciaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(bairroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vincularMedJButton)
                                .addGap(27, 27, 27)
                                .addComponent(vizualizarMedJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(novoMedJButton))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(hospitalJRadioButton)
                            .addComponent(consultorioJRadioButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(cadastrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(160, 100, 1112, 575);
    }// </editor-fold>                        

    private void cadastrarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	
    	if(nomeJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Nome é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(cnpjJFormattedTextField.getText().equals("  .   .   /    -  ")){
    		JOptionPane.showMessageDialog(null, "Campo CNPJ é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(telefoneJFormattedTextField.getText().equals("(  )     -    ")){
    		JOptionPane.showMessageDialog(null, "Campo Telefone é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(ruaJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Rua é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(numeroJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Número é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(bairroJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Bairro é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(cepJFormattedTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo CEP é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(cidadeJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Cidade é obrigatório", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(buttonGroup1.getSelection() == null){
    		JOptionPane.showMessageDialog(null, "Selecione o tipo do Estabelecimento", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(especialidades.isEmpty()){
    		JOptionPane.showMessageDialog(null, "Vincule pelo menos uma especialidade para poder efetuar o cadastro", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else if(medicos.isEmpty()){
    		JOptionPane.showMessageDialog(null, "Vincule pelo menos um médico para poder efetuar o cadastro", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}else{
    		
    		ArrayList<String> crms = new ArrayList<String>();
			for(Medico med : medicos){
				crms.add(med.getCrm());
			}
    		try{
	    		if(hospitalJRadioButton.isSelected()){
	    			new Cadastradora().cadastrarHospital(Cnpj.getInstance(cnpjJFormattedTextField.getText()), nomeJTextField.getText(), ruaJTextField.getText(), numeroJTextField.getText(), bairroJTextField.getText(), cidadeJTextField.getText(), referenciaJTextField.getText(), cepJFormattedTextField.getText(), estadoJComboBox.getSelectedItem().toString(), crms, especialidades);
	    		}else{
	    			new Cadastradora().cadastrarClinica(Cnpj.getInstance(cnpjJFormattedTextField.getText()), nomeJTextField.getText(), ruaJTextField.getText(), numeroJTextField.getText(), bairroJTextField.getText(), cidadeJTextField.getText(), referenciaJTextField.getText(), cepJFormattedTextField.getText(), estadoJComboBox.getSelectedItem().toString(), crms, especialidades);
	    		}
	    		JOptionPane.showMessageDialog(null, "Estabelecimento médico cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    		dispose();
    		}catch(InsertionException iex){
    			JOptionPane.showMessageDialog(null, iex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}catch (DoctorNotFoundException dnfex) {
    			JOptionPane.showMessageDialog(null, dnfex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }                                                

    private void novoMedJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	new CadastrarMedico().setVisible(true);
    }                                              

    private void vizualizarMedJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	String str = "";
    	for(Medico med : medicos){
    		String aux = "";
    		
    		for(Especialidade esp : med.getEspecialidades()){
    			aux += String.format("    Nome: %s\n    Descricao: %s\n", esp.getNome(), esp.getDescricao());
    		}
    		
			str += String.format("Nome: %s\nCRM: %s\nEspecialidade(s):\n%s\n", med.getNome(), med.getCrm(), med.getEspecialidades().isEmpty() ? "    nenhuma" : Formatadora.format(aux, 65));
		}
    	new Exibir(str).setVisible(true);
    }                                                    

    private void vincularMedJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if(medicosJList.getSelectedValue() == null){
        	JOptionPane.showMessageDialog(null, "Selecione um médico!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	medicos.add((Medico) medicosJList.getSelectedValue());
        	JOptionPane.showMessageDialog(null, "Médico vinculado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        	modelMed.remove(medicosJList.getSelectedIndex());
        }
    }                                                  

    private void novaEspJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        new CadastrarEspecialidade().setVisible(true);
    }                                              

    private void visualizarEspJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	String str = "";

		for (Especialidade esp : especialidades) {
			str += String.format("Nome: %s\nDescricao: %s\n\n", esp.getNome(),
					Formatadora.format(esp.getDescricao(), 57));
		}
    	
    	new Exibir(str).setVisible(true);
    }                                                    

    private void vincularEspJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	if(especialidadesJList.getSelectedValue() == null){
    		JOptionPane.showMessageDialog(null, "Selecione uma especialidade!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else{
    		especialidades.add((Especialidade)especialidadesJList.getSelectedValue());
    		JOptionPane.showMessageDialog(null, "Especialidade vinculada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    		modelEsp.remove(especialidadesJList.getSelectedIndex());
    	}
    }                                                  

    private void detalhesJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	if(especialidadesJList.getSelectedValue() == null){
    		JOptionPane.showMessageDialog(null, "Selecione uma especialidade!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else{
	    	Especialidade esp = (Especialidade) especialidadesJList.getSelectedValue();
	    	detalhesJTextArea.setText(Formatadora.format(esp.getDescricao(), 41));
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
            java.util.logging.Logger.getLogger(CadastrarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarEstabelecimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField bairroJTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cadastrarJButton;
    private javax.swing.JFormattedTextField cepJFormattedTextField;
    private javax.swing.JTextField cidadeJTextField;
    private javax.swing.JFormattedTextField cnpjJFormattedTextField;
    private javax.swing.JRadioButton consultorioJRadioButton;
    private javax.swing.JButton detalhesJButton;
    private javax.swing.JTextArea detalhesJTextArea;
    private javax.swing.JLabel especialidadeJLabel;
    private static javax.swing.JList especialidadesJList;
    private javax.swing.JComboBox estadoJComboBox;
    private javax.swing.JRadioButton hospitalJRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private static javax.swing.JList medicosJList;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JButton novaEspJButton;
    private javax.swing.JButton novoMedJButton;
    private javax.swing.JTextField numeroJTextField;
    private javax.swing.JTextField referenciaJTextField;
    private javax.swing.JTextField ruaJTextField;
    private javax.swing.JFormattedTextField telefoneJFormattedTextField;
    private javax.swing.JButton vincularEspJButton;
    private javax.swing.JButton vincularMedJButton;
    private javax.swing.JButton visualizarEspJButton;
    private javax.swing.JButton vizualizarMedJButton;
    // End of variables declaration                   
}
