package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Menu;
import model.MenuTableModel;

public class MenuView extends JFrame implements ActionListener{
	private JLabel			   
      labelNomePrato, labelNomeBebida, labelNomeSobremesa, labelNumProduto, labelNomeProduto, labelDescProduto, 
      labelValorProduto, labelTipoProduto;
	private JTextField		textNumProduto, textNomeProduto, textDispProduto, textValorProduto, textDescProduto;
	private JButton			
      buttonConsultar, buttonCadastrar, buttonAlterar, buttonExcluir, buttonCancelar;
	private JComboBox       comboTipoProduto;
	private JRadioButton	radioAtivo, radioDesativo;
	private JTable          tableProduto;
	private MenuTableModel tableModel;
	private JScrollPane     scroll;
	private JPanel 			
      panelNorth, panelCenter, panelSouth, panelTabPrato, panelTabBebida, panelTabSobremesa;
	private JFrame			janela;
	private JTabbedPane 	tabbedMenu;
	private ResourceBundle	bn=null;
	private Menu menu;
                                   
	public MenuView(ResourceBundle bundle){
		super();		
		
		bn = bundle; 
		
		labelNumProduto   = new JLabel(bn.getString("labelNumProduto"));
		labelNomeProduto  = new JLabel(bn.getString("labelNomeProduto"));
		labelDescProduto  = new JLabel(bn.getString("labelDescProduto"));
		labelValorProduto = new JLabel(bn.getString("labelValorProduto"));  
		labelTipoProduto  = new JLabel(bn.getString("labelTipoProduto"));	
		
		tableProduto = new JTable();
	}
	
	//CONSULTAR/EDITAR CARDAPIO
	public void editMenuView(int idProduct){
		//Layouts
		janela = new JFrame(bn.getString("tituloEditarMenuView"));
		janela.setLayout(new BorderLayout(10,10));
      
		//NORTH
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(2, 2, 10, 10));      
      
		textNumProduto    = new JTextField(10);
		textNomeProduto   = new JTextField(10);
      
		panelNorth.add(labelNumProduto);
		panelNorth.add(textNumProduto);
		panelNorth.add(labelNomeProduto);
		panelNorth.add(textNomeProduto);
      
		janela.add(panelNorth, BorderLayout.NORTH);	
		
		//CENTER
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 3, 10, 10));     
            
		buttonConsultar   = new JButton(bn.getString("buttonConsultar"));
		buttonAlterar     = new JButton(bn.getString("buttonAlterar"));
		buttonExcluir     = new JButton(bn.getString("buttonExcluir"));
      
		panelCenter.add(buttonConsultar);
		panelCenter.add(buttonAlterar);
		panelCenter.add(buttonExcluir);
      
		janela.add(panelCenter, BorderLayout.CENTER);	
      
		//SOUTH
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
            
		loadTable(idProduct); 
      
		janela.add(panelSouth, BorderLayout.SOUTH);	
		
		buttonConsultar.addActionListener(this);
		buttonAlterar.addActionListener(this);
		buttonExcluir.addActionListener(this);
      		
		//DEFININDO
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(480,562);
		janela.setLocationRelativeTo(null);
	}
	
	public void loadTable(int idProduto){
   		panelSouth.add(carregarScroll(idProduto));
	}	
	
	public void loadTable(String productName){
   		panelSouth.add(carregarScroll(productName));
	}	
	
	//CONSULTA COM ID OU NOME
	//SE 0 - RETORNA TODOS OS DADOS DA TABELA MENU
	private JScrollPane carregarScroll(int productId){
    	if(productId == 0) {
    		scroll = new JScrollPane(getTblProdutos());
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	} else {
    		scroll = new JScrollPane(getTblProdutos(productId));
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	}		
	}	
	
	private JScrollPane carregarScroll(String productName){    	
		scroll = new JScrollPane(getTblProdutos(productName));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		return scroll;    		    		
	}
	
	private JTable getTblProdutos() {   
    	tableProduto.setModel(getTableModel());       
        return tableProduto;
    }
	
	private JTable getTblProdutos(int productId) {		
		tableProduto.setModel(getTableModel(productId));        
        return tableProduto;
    }
	
	private JTable getTblProdutos(String productName) {		
		tableProduto.setModel(getTableModel(productName));        
        return tableProduto;
    }
 
    private MenuTableModel getTableModel() {
    	menu = new Menu();    	
        tableModel = new MenuTableModel(menu.consultMenu(), bn) {
        	//
        }; 
        return tableModel;
    }
        
    private MenuTableModel getTableModel(int productId) {
    	menu = new Menu();    	
        tableModel = new MenuTableModel(menu.consultMenu(productId), bn);        
        return tableModel;
    }
	
    private MenuTableModel getTableModel(String productName) {
    	menu = new Menu();    	
        tableModel = new MenuTableModel(menu.consultMenu(productName), bn);        
        return tableModel;
    }
   
	//INCLUIR CARDAPIO
	public void includeMenuView(){
		//Layouts
		janela = new JFrame(bn.getString("tituloincluirCardapioView"));
		janela.setLayout(new BorderLayout(10,10));
      
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(6, 2, 10, 10));      
      
		labelNumProduto = new JLabel(bn.getString("labelNumProduto"));
		
		textNomeProduto   	= new JTextField(10);
		textDescProduto    	= new JTextField(10);
		textValorProduto    = new JTextField(10);
		textDispProduto    	= new JTextField(10);
		textNumProduto		= new JTextField(10);
		
		radioAtivo 		= new JRadioButton("Ativo", true);
		radioDesativo 	= new JRadioButton("Desativo", false);
		
		comboTipoProduto = new JComboBox();
		comboTipoProduto.setBackground(Color.WHITE);
		comboTipoProduto.addItem("----");
		comboTipoProduto.addItem(bn.getString("comboPrato"));
		comboTipoProduto.addItem(bn.getString("comboSobremesa"));
		comboTipoProduto.addItem(bn.getString("comboBebida"));  
      
		radioAtivo.addActionListener(this);
      	radioDesativo.addActionListener(this);
		
      	panelNorth.add(labelNumProduto);
		panelNorth.add(textNumProduto);
		panelNorth.add(labelNomeProduto);
		panelNorth.add(textNomeProduto);
		panelNorth.add(labelValorProduto);
		panelNorth.add(textValorProduto);
		panelNorth.add(labelDescProduto);
		panelNorth.add(textDescProduto); //<--- COLOCAR JTEXTAREA
      	panelNorth.add(labelTipoProduto);
      	panelNorth.add(comboTipoProduto);
      	panelNorth.add(radioAtivo);
      	panelNorth.add(radioDesativo);
      	
      	      	
      
		janela.add(panelNorth, BorderLayout.NORTH);	
		
		//Center
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 2, 10, 10));     
            
		buttonCadastrar   = new JButton(bn.getString("buttonCadastrar"));
		buttonCancelar    = new JButton(bn.getString("buttonCancelar"));
      
      	panelCenter.add(buttonCadastrar);
      	panelCenter.add(buttonCancelar);
      	
      	buttonCadastrar.addActionListener(this);
      	buttonCancelar.addActionListener(this);
      
		janela.add(panelCenter, BorderLayout.CENTER);	     	
      		
		//Definicoes
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(300,280);	
		janela.setLocationRelativeTo(null);
	}

   
	//CARDAPIO
	public void vizualizarCardapioView(){
		//Layouts
		janela = new JFrame(bn.getString("tituloVisualizarCardapioView"));
		janela.setLayout(new BorderLayout(10,10));
		
		
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT));	
		
		panelTabPrato		= new JPanel();
		panelTabBebida		= new JPanel();
		panelTabSobremesa	= new JPanel();
		panelTabPrato.setPreferredSize(new Dimension(300, 250));
		
		
		labelNomePrato = new JLabel("nomePrato | descri��o | disponibilidade");		
		panelTabPrato.add(labelNomePrato);
		
		labelNomeBebida = new JLabel("nomeBebida | descri��o | disponibilidade");
		panelTabBebida.add(labelNomeBebida);
		
		labelNomeSobremesa = new JLabel("nomeSobremesa | descri��o | disponibilidade");
		panelTabSobremesa.add(labelNomeSobremesa);
		
		
		tabbedMenu = new JTabbedPane();
		//ImageIcon icon = createImageIcon("icon.prato.png");
		tabbedMenu.addTab("Prato",  	panelTabPrato);
		tabbedMenu.addTab("Bebida",		panelTabBebida);
		tabbedMenu.addTab("Sobremesa", 	panelTabSobremesa);
		//tabbedMenu.addTab("Tab 1", icon, panelTabPratos, "Pratos");
		
		panelNorth.add(tabbedMenu);
		janela.add(panelNorth, BorderLayout.NORTH);	
			
		//Definicoes
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(300,200);	
		janela.setLocationRelativeTo(null);		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//RADIOBUTTON
		if(event.getSource() == radioAtivo){ 
			radioDesativo.setSelected(false);
		}
		if(event.getSource() == radioDesativo){ 
			radioAtivo.setSelected(false);
		}
		//BUTTON CADASTRAR
        if(event.getSource() == buttonCadastrar){
        	if(	(textNomeProduto == null)  || (textDescProduto == null) || 
        		(textValorProduto == null) || (textDispProduto == null) || 
        		(textNumProduto == null) || (comboTipoProduto.getSelectedIndex() == 0) ) {        		
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroCampoBranco"));        		
        	} else {       	
	        		    		  
        		menu = new Menu();
        		
        		try {
        			menu.setIdProduct(Integer.parseInt(textNumProduto.getText()));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemIdInvalido"));	  
	        		return;
	        	}
        		
        		try {
	        		menu.setProductUniqueAmount(Double.parseDouble(textValorProduto.getText() ));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemValorInvalido"));	  
	        		return;
	        	}	        		
        		
        		menu.setProductName	(textNomeProduto.getText());
        		menu.setProductDescription(textDescProduto.getText());        		
        		menu.setProductType	(comboTipoProduto.getSelectedIndex());
        		
        		if(radioAtivo.isSelected()){        				
        			menu.setProductAvailable(true);
				}else if(radioDesativo.isSelected()){			
					menu.setProductAvailable(false);;
				}
        		
        		if(menu.registerMenu()) {
        			JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroSucesso"));
        			textNumProduto.setText(null);
        			textNomeProduto.setText(null);
        			textDescProduto.setText(null);
        			textValorProduto.setText(null);
        			textDispProduto.setText(null);
	        		comboTipoProduto.setSelectedIndex(0);        			
        		}	    	  		        		
        	}
        }  
        //BUTTON CANCELAR
        if(event.getSource() == buttonCancelar){
        	this.dispose();
        	janela.setDefaultCloseOperation( EXIT_ON_CLOSE );
        } 
        
        //BUTTON - CONSULTAR
        if(event.getSource() == buttonConsultar){
        	menu = new Menu();
        	
			String nomeUsuario = textNomeProduto.getText();
			int idUsuario = 0;			
			
			if(!(nomeUsuario.equals(""))){	
				try {					
					panelSouth.removeAll();
					loadTable(nomeUsuario);
					if(tableModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	
					} else {
		    			//.validate() = m�todo para atualizar as informa��es
		    			panelSouth.validate();
		    			JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaSucesso"));	
					}	    			
	        	}
	        	catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	    		  
	        	} 
				
			} else if(nomeUsuario.equals("")) {
				try {	    		    
	    			idUsuario = Integer.parseInt(textNumProduto.getText());   			
	        	}
	        	catch(NumberFormatException e) {
	        		
	        	}				
				if (idUsuario != 0) {
					try {	    		    		    				    			
						panelSouth.removeAll();
						loadTable(idUsuario);
						if(tableModel.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	
						} else {
			    			//.validate() = m�todo para atualizar as informa��es
			    			panelSouth.validate();
			    			JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaSucesso"));	
						}
		    			
		        	}
		        	catch(NumberFormatException e) {
		        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
		        	} 					
				} else {					
					JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	 
				}					
			}
        }
        
        //BUTTON - ALTERAR
        if(event.getSource() == buttonAlterar){
        	menu = new Menu();  
    		try {	    
    			int linhaSelecionada = tableProduto.getSelectedRow();
    			int colunaSelecionada = tableProduto.getSelectedColumn();
    			menu = tableModel.getMenu(linhaSelecionada);  				
    			menu.changeMenu(menu.getIdProduct());
            	panelSouth.removeAll();
    			loadTable(menu.getIdProduct());
            	
    			panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemAlterarSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}            	
        }
        
        //BUTTON - EXCLUIR
        if(event.getSource() == buttonExcluir){
        	menu = new Menu();  
    		try {	    
    			int linhaSelecionada = tableProduto.getSelectedRow();        	
    			menu = tableModel.getMenu(linhaSelecionada);
    			menu.deleteUser(menu.getIdProduct());
            	tableModel.removeCardapio(linhaSelecionada);        
            	panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemExcluirSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}      	
        }
		
	}
}

