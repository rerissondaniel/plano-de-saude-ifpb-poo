package br.edu.planodesaude.GUI.medico;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import br.edu.planodesaude.GUI.main.Exibir;
import br.edu.planodesaude.controle.Cadastradora;
import br.edu.planodesaude.controle.Pesquisadora;
import br.edu.planodesaude.dominio.Especialidade;
import br.edu.planodesaude.exceptions.InsertionException;
import br.edu.planodesaude.exceptions.SearchException;
import br.edu.planodesaude.util.Formatadora;

public class CadastrarMedico extends javax.swing.JInternalFrame {

	private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
	private static DefaultListModel model = new DefaultListModel();
	
	private static void initJList(){
		
		model.clear();
		
		try {
			ArrayList<Especialidade> esp = new Pesquisadora().getEspecialidades();
			
			for(Especialidade especialidade : esp){
				model.addElement(especialidade);
			}
			
		} catch (SearchException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		}
        
        especialidadesJList.setModel(model);
	}
	
    public CadastrarMedico() {
        
    	setTitle("Cadastrar Médico");
    	setLocation((int)JDesktopPane.CENTER_ALIGNMENT, (int)JDesktopPane.CENTER_ALIGNMENT);
    	setClosable(true);
    	initComponents();
    	initJList();
    	setVisible(true);
    }
                          
    private void initComponents() {

        nomeJLabel = new javax.swing.JLabel();
        crmJLabel = new javax.swing.JLabel();
        especialidadeJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        especialidadesJList = new javax.swing.JList();
        detalhesJButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        detalhesJTextArea = new javax.swing.JTextArea();
        crmJTextField = new javax.swing.JTextField();
        nomeJTextField = new javax.swing.JTextField();
        vincularJButton = new javax.swing.JButton();
        novaEspecialidadeJButton = new javax.swing.JButton();
        cadastrarJButton = new javax.swing.JButton();
        visualizarJButton = new javax.swing.JButton();
        atualizarJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nomeJLabel.setText("Nome");

        crmJLabel.setText("CRM");

        especialidadeJLabel.setText("Especialidade(s)");

        especialidadesJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(especialidadesJList);

        detalhesJButton.setText("Detalhes");
        detalhesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesJButtonActionPerformed(evt);
            }
        });

        detalhesJTextArea.setColumns(20);
        detalhesJTextArea.setRows(5);
        detalhesJTextArea.setEditable(false);
        jScrollPane2.setViewportView(detalhesJTextArea);

        vincularJButton.setText("Vincular Especialidade");
        vincularJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vincularJButtonActionPerformed(evt);
            }
        });

        novaEspecialidadeJButton.setText("Nova Especialidade");
        novaEspecialidadeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novaEspecialidadeJButtonActionPerformed(evt);
            }
        });

        cadastrarJButton.setText("Cadastrar");
        cadastrarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarJButtonActionPerformed(evt);
            }
        });

        visualizarJButton.setText("Visualizar Especialidades Vinculadas");
        visualizarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarJButtonActionPerformed(evt);
            }
        });

        atualizarJButton.setText("Atualizar");
        atualizarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(novaEspecialidadeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crmJLabel)
                                    .addComponent(nomeJLabel))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(especialidadeJLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(atualizarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(detalhesJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(198, 208, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addComponent(vincularJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2)
                                    .addComponent(visualizarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(cadastrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nomeJLabel)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(crmJLabel)
                    .addComponent(crmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(especialidadeJLabel)
                    .addComponent(atualizarJButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(detalhesJButton)
                            .addComponent(vincularJButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(visualizarJButton)
                    .addComponent(novaEspecialidadeJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cadastrarJButton)
                .addContainerGap())
        );

        setBounds(395, 134, 578, 447);
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

    private void vincularJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	if(especialidadesJList.getSelectedValue() == null){
    		JOptionPane.showMessageDialog(null, "Selecione uma especialidade!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else{
    		
    		boolean controle = false;
    		
    		for(Especialidade especialidade : especialidades){
    			if(especialidade.equals(especialidadesJList.getSelectedValue())){
    				controle = true;
    				break;
    			}
    		}
    		
    		if(controle){
    			JOptionPane.showMessageDialog(null, "A especialidade selecionada já está vinculada à esse médico", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    		}else{
	    		especialidades.add((Especialidade)especialidadesJList.getSelectedValue());
	    		JOptionPane.showMessageDialog(null, "Especialidade vinculada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    		model.remove(especialidadesJList.getSelectedIndex());
    		}
    	}
    }                                                        

    private void visualizarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	String str = "";

		for (Especialidade esp : especialidades) {
			str += String.format("Nome: %s\nDescricao: %s\n\n", esp.getNome(),
					Formatadora.format(esp.getDescricao(), 57));
		}
    	
    	new Exibir(str).setVisible(true);
    }                                                 

    private void cadastrarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	if(nomeJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo Nome obrigatório!", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}
    	else if(crmJTextField.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Campo CRM obrigatório!", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}
    	else if(especialidades.isEmpty()){
    		JOptionPane.showMessageDialog(null, "Vincule pelo menos uma especialidade!", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}
    	else{
    		try{
    			new Cadastradora().cadastrarMedico(crmJTextField.getText(), nomeJTextField.getText(), especialidades);
    			JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    			dispose();
    		}catch(InsertionException ex){
    			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }                                                

    private void novaEspecialidadeJButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	new CadastrarEspecialidade();
    }                                                       

    private void atualizarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	initJList();
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
            java.util.logging.Logger.getLogger(CadastrarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarMedico().setVisible(true);
                initJList();
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton atualizarJButton;
    private javax.swing.JButton cadastrarJButton;
    private javax.swing.JLabel crmJLabel;
    private javax.swing.JTextField crmJTextField;
    private javax.swing.JButton detalhesJButton;
    private javax.swing.JTextArea detalhesJTextArea;
    private javax.swing.JLabel especialidadeJLabel;
    private static javax.swing.JList especialidadesJList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nomeJLabel;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JButton novaEspecialidadeJButton;
    private javax.swing.JButton vincularJButton;
    private javax.swing.JButton visualizarJButton;
    // End of variables declaration                   
}
