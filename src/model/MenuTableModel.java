package model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

public class MenuTableModel extends AbstractTableModel{
	// Lista de Sócios a serem exibidos na tabela
	private List<Menu> linhas;
	private ResourceBundle	bn=null;
	
	// Array com os nomes das colunas.
    private String[] colunas;
    
    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int DESCRICAO = 2;
    private static final int VALOR = 3;
    private static final int DISPONIBILIDADE = 4;
    private static final int TIPO = 5;
	
    // Cria um UsuarioTableModel sem nenhuma linha
    public MenuTableModel(ResourceBundle bundle) {
    	bn = bundle;
    	colunas = new String[]  
			{	bn.getString("columnID"), 
				bn.getString("columnNome"), 
				bn.getString("columnDescricao"),
				bn.getString("columnValorUnitario"), 
				bn.getString("columnDisponibilidade"), 
				bn.getString("columnTipo") 
			};
        linhas = new ArrayList<Menu>();
         
    }
    
    // Cria um UsuarioTableModel contendo a lista recebida por parâmetro
    public MenuTableModel(List<Menu> listaDeMenus, ResourceBundle bundle) {
    	bn = bundle;
    	colunas = new String[]  
    			{	bn.getString("columnID"), 
					bn.getString("columnNome"), 
					bn.getString("columnDescricao"),
					bn.getString("columnValorUnitario"), 
					bn.getString("columnDisponibilidade"), 
					bn.getString("columnTipo")  
    			};
        linhas = new ArrayList<Menu>(listaDeMenus);         
    }
    
    @Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Menu pruduto = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
	    	pruduto.setIdProduct((Integer) aValue);
	        break;
	    case NOME:
	    	pruduto.setProductName((String) aValue);
	        break;
	    case DESCRICAO:
	    	pruduto.setProductDescription((String) aValue);
	        break;
	    case VALOR:
	    	pruduto.setProductUniqueAmount((Double) aValue);
	        break;
	    case DISPONIBILIDADE:
	    	pruduto.setProductAvailable((Boolean) aValue);
            break;
	    case TIPO:
	    	pruduto.setProductType((Integer) aValue);
            break;
	    default:
	        // Não deve ocorrer, pois só existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Menu produto = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
            return produto.getIdProduct();
	    case NOME:
	        return produto.getProductName();
	    case DESCRICAO:
	        return produto.getProductDescription();
	    case VALOR:
            return produto.getProductUniqueAmount();
	    case DISPONIBILIDADE:
            return produto.isProductAvailable();
	    case TIPO:
            return produto.getProductType();
	    default:
	      
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	}	
	
	@Override
	public String getColumnName(int columnIndex) {
	    return colunas[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    switch (columnIndex) {
	    case ID:
	        return Integer.class;
	    case NOME:
	        return String.class;
	    case DESCRICAO:
            return String.class;
	    case VALOR:
            return Double.class;
	    case DISPONIBILIDADE:
            return Boolean.class;
	    case TIPO:
            return Integer.class;
	    default:
	      
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       
		return true;
    }
	
	
	public Menu getMenu(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	
	public void addMenu(Menu cardapio) {
	   
	    linhas.add(cardapio);
	 
	  
	    int ultimoIndice = getRowCount() - 1;
	 
	    
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	
	public void removeCardapio(int indiceLinha) {
	  
	    linhas.remove(indiceLinha);
	
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	public void addListaDeCardapios (List<Menu> cardapios) {
	  
	    int indice = getRowCount();
	 
	  
	    linhas.addAll(cardapios);
	 
	  
	    fireTableRowsInserted(indice, indice + cardapios.size());
	}
	 

	public void limpar() {
	
	    linhas.clear();
	 
	    
	    fireTableDataChanged();
	}
}
