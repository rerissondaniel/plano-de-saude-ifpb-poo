package br.edu.planodesaude.GUI.estabelecimento;

import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import br.edu.planodesaude.controle.Alteradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.dominio.EstabelecimentoMedico;
import br.edu.planodesaude.dominio.Hospital;
import br.edu.planodesaude.dominio.Medico;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.exceptions.UpdateException;
import br.edu.planodesaude.util.Cnpj;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.planodesaude.util.Endereco;

/**
 *
 * @author gustavo
 */
public class AtualizarEstabelecimento extends javax.swing.JInternalFrame {
	
	private static DefaultListModel modelEsp = new DefaultListModel();
	private static DefaultListModel modelMed = new DefaultListModel();
	
	public void initRemoveEsp(ArrayList<Especialidade> especialidades){
		DefaultListModel modelRemove = new DefaultListModel();
		
		modelRemove.clear();
		
		for(Especialidade especialidade : especialidades){
			modelRemove.addElement(especialidade);
		}
	        
	    espRemoveJList.setModel(modelRemove);
	}
	
	
	private static void initAddEsp(){
		
		modelEsp.clear();
		
		try {
			ArrayList<Especialidade> esp = new Pesquisadora().getEspecialidades();
			
			for(Especialidade especialidade : esp){
				modelEsp.addElement(especialidade);
			}
			
		} catch (SearchException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		}
        
        espAddJList1.setModel(modelEsp);
	}
	
	public void initRemoveMed(ArrayList<Medico> medicos){
		DefaultListModel modelRemove = new DefaultListModel();
		
		for(Medico especialidade : medicos){
			modelRemove.addElement(especialidade);
		}
	        
	    medicosRemoveJList.setModel(modelRemove);
	}
	
	public void initAddMed(){
		modelMed.clear();
		
		try {
			ArrayList<Medico> medicos = new Pesquisadora().getMedicos();
			
			for(Medico medico : medicos){
				modelMed.addElement(medico);
			}
			
		} catch (SearchException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		}
        
        medicosAddJList.setModel(modelMed);
	}
	
    public AtualizarEstabelecimento() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cnpjJFormattedTextField = new javax.swing.JFormattedTextField();
        buscarJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nomeJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ruaJTextField = new javax.swing.JTextField();
        numeroJTextField = new javax.swing.JTextField();
        referenciaJTextField = new javax.swing.JTextField();
        bairroJTextField = new javax.swing.JTextField();
        cepJFormattedTextField = new javax.swing.JFormattedTextField();
        cidadeJTextField = new javax.swing.JTextField();
        estadoJComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        espRemoveJList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        espAddJList1 = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        removerEspJButton = new javax.swing.JButton();
        vincularEspJButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        removerMedJButton = new javax.swing.JButton();
        vincularMedJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        medicosRemoveJList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        medicosAddJList = new javax.swing.JList();
        alterarJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CNPJ");

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

        jLabel2.setText("Nome");

        jLabel4.setText("Endereço");

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel7.setText("Referência");

        jLabel8.setText("Bairro");

        jLabel9.setText("CEP");

        jLabel10.setText("Cidade");

        jLabel11.setText("Estado");

        try {
            cepJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        estadoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        espRemoveJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        espRemoveJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(espRemoveJList);

        espAddJList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        espAddJList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(espAddJList1);

        jLabel12.setText("Especialidade(s) Vinculadas(s)");

        jLabel13.setText("Especialidade(s)");

        removerEspJButton.setText("Remover");
        removerEspJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerEspJButtonActionPerformed(evt);
            }
        });

        vincularEspJButton.setText("Vincular");
        vincularEspJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vincularEspJButtonActionPerformed(evt);
            }
        });

        jLabel14.setText("Medico(s) Responsável(is)");

        jLabel15.setText("Medico(s)");

        removerMedJButton.setText("Remover");
        removerMedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerMedJButtonActionPerformed(evt);
            }
        });

        vincularMedJButton.setText("Vincular");
        vincularMedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vincularMedJButtonActionPerformed(evt);
            }
        });

        medicosRemoveJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        medicosRemoveJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(medicosRemoveJList);

        medicosAddJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        medicosAddJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(medicosAddJList);

        alterarJButton.setText("Alterar");
        alterarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(referenciaJTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nomeJTextField)
                                    .addComponent(ruaJTextField)
                                    .addComponent(cidadeJTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(bairroJTextField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buscarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14)
                                    .addComponent(removerMedJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(vincularMedJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 67, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane1)
                                    .addComponent(removerEspJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jLabel13)
                                    .addComponent(vincularEspJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alterarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cnpjJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buscarJButton))
                            .addComponent(jLabel1))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ruaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removerEspJButton)
                                        .addComponent(vincularEspJButton)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addComponent(numeroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(referenciaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bairroJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cepJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(estadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(vincularMedJButton)
                                    .addComponent(removerMedJButton)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(alterarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        setBounds(162, 68, 1053, 586);
    }// </editor-fold>                        
    
    EstabelecimentoMedico estab;
    
    private void buscarJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(cnpjJFormattedTextField.getText().equals("  .   .   /    -  ")){
        	JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	try {
				estab = new Pesquisadora().pesquisarEstabelecimento(cnpjJFormattedTextField.getText());
				cnpjJFormattedTextField.setEditable(false);
				nomeJTextField.setText(estab.getNome());
				ruaJTextField.setText(estab.getEndereco().getRua());
				numeroJTextField.setText(estab.getEndereco().getNumero());
				referenciaJTextField.setText(estab.getEndereco().getReferencia());
				bairroJTextField.setText(estab.getEndereco().getBairro());
				cidadeJTextField.setText(estab.getEndereco().getCidade());
				cepJFormattedTextField.setText(estab.getEndereco().getCep());
				estadoJComboBox.setSelectedItem(estab.getEndereco().getEstado());
				
				initRemoveEsp(estab.getEspecialidades());
				initAddEsp();
				initRemoveMed(estab.getMedico());
				initAddMed();
			} catch (SearchException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
        }
    }                                             

    private void removerMedJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(estab == null){
        	JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else if(medicosRemoveJList.getSelectedValue() == null){
        	JOptionPane.showMessageDialog(null, "Selecione um médico", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	Medico med = (Medico)medicosRemoveJList.getSelectedValue();
        	estab.getMedico().remove(med);
        	initRemoveMed(estab.getMedico());
        }
    }                                                 

    private void vincularMedJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	if(estab == null){
        	JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	Medico med = (Medico)medicosAddJList.getSelectedValue();
        	
        	if(!estab.getMedico().contains(med)){
        		estab.getMedico().add(med);
        		initRemoveMed(estab.getMedico());
        	}else{
        		JOptionPane.showMessageDialog(null, "Médico já vinculado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
    }                                                  

    private void vincularEspJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	if(estab == null){
        	JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	Especialidade esp = (Especialidade)espAddJList1.getSelectedValue();
        	
        	boolean ok = true;
        	
        	for(Especialidade espes : estab.getEspecialidades()){
        		if(espes.equals(esp)){
        			JOptionPane.showMessageDialog(null, "Especialidade já vinculada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        			ok = false;
        			break;
        		}
        	}
        	
        	if(ok){
        		estab.getEspecialidades().add(esp);
        		initRemoveEsp(estab.getEspecialidades());
        	}
        }
    }                                                  

    private void removerEspJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if(estab == null){
        	JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else if(espRemoveJList.getSelectedValue() == null){
        	JOptionPane.showMessageDialog(null, "Selecione uma especialidade", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else{
        	Especialidade esp = (Especialidade)espRemoveJList.getSelectedValue();
        	
        	for(int i = 0; i < estab.getEspecialidades().size(); i++){
        		if(estab.getEspecialidades().get(i).equals(esp)){
        			estab.getEspecialidades().remove(i);
        			break;
        		}
        	}
        	
        	initRemoveEsp(estab.getEspecialidades());
        }
    }                                                 

    private void alterarJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	 if(nomeJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo nome obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(ruaJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo rua obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
	        }else 
	        if(numeroJTextField.getText().equals("")){
	        	JOptionPane.showMessageDialog(null, "Campo numero obrigatório", "Alerta!", JOptionPane.WARNING_MESSAGE);
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
	        	Endereco endereco = new Endereco(
	        			ruaJTextField.getText(), 
	        			numeroJTextField.getText(), 
	        			bairroJTextField.getText(), 
	        			cidadeJTextField.getText(), 
	        			referenciaJTextField.getText(), 
	        			cepJFormattedTextField.getText(), 
	        			estadoJComboBox.getSelectedItem().toString());
	        	estab.setEndereco(endereco);
	        	estab.setNome(nomeJTextField.getText());
	        	
	        	
	        	if(estab == null){
	        		JOptionPane.showMessageDialog(null, "Informe o CNPJ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	        	}else{
	        		try{
	        			Alteradora alter = new Alteradora();
	        			alter.alterarEstabelecimento(estab);
	        			JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        			dispose();
	        		} catch(UpdateException uex){
	        			uex.printStackTrace();
	        			JOptionPane.showMessageDialog(null, uex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        		}
	        	}
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
            java.util.logging.Logger.getLogger(AtualizarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarEstabelecimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton alterarJButton;
    private javax.swing.JTextField bairroJTextField;
    private javax.swing.JButton buscarJButton;
    private javax.swing.JFormattedTextField cepJFormattedTextField;
    private javax.swing.JTextField cidadeJTextField;
    private javax.swing.JFormattedTextField cnpjJFormattedTextField;
    private static javax.swing.JList espAddJList1;
    private javax.swing.JList espRemoveJList;
    private javax.swing.JComboBox estadoJComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList medicosAddJList;
    private javax.swing.JList medicosRemoveJList;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JTextField numeroJTextField;
    private javax.swing.JTextField referenciaJTextField;
    private javax.swing.JButton removerEspJButton;
    private javax.swing.JButton removerMedJButton;
    private javax.swing.JTextField ruaJTextField;
    private javax.swing.JButton vincularEspJButton;
    private javax.swing.JButton vincularMedJButton;
    // End of variables declaration                   
}