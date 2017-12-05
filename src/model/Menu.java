package model;

import java.io.Serializable;
import java.util.List;

import dao.MenuDAO;
import to.MenuTo;

public class Menu implements Serializable {	

	private String nomeProduto, descricaoProduto;
	private int idProduto, tipoProduto;
	private double valorUnitarioProduto;
	private boolean disponibilidadeProduto;
	private MenuDAO dao;
	private MenuTo to;
    private static final long serialVersionUID = 1L;
	
	public Menu() {
		
	}	

	public Menu(int idProduct, String productName, String productDescription, double productUniqueAmount, 
			boolean productAvailable, int productType) {
		this.idProduto = idProduct;
		this.nomeProduto = productName;
		this.descricaoProduto = productDescription;
		this.valorUnitarioProduto = productUniqueAmount;		
		this.disponibilidadeProduto = productAvailable;
		this.tipoProduto = productType;
	}
	
	public String getProductName() {
		return nomeProduto;
	}
	public void setProductName(String productName) {
		this.nomeProduto = productName;
	}
	public String getProductDescription() {
		return descricaoProduto;
	}
	public void setProductDescription(String productDescription) {
		this.descricaoProduto = productDescription;
	}
	public int getProductType() {
		return tipoProduto;
	}
	public void setProductType(int productType) {
		this.tipoProduto = productType;
	}
	public int getIdProduct() {
		return idProduto;
	}
	public void setIdProduct(int idProduct) {
		this.idProduto = idProduct;
	}
	public double getProductUniqueAmount() {
		return valorUnitarioProduto;
	}
	public void setProductUniqueAmount(double productUniqueAmount) {
		this.valorUnitarioProduto = productUniqueAmount;
	}
	public boolean isProductAvailable() {
		return disponibilidadeProduto;
	}
	public void setProductAvailable(boolean productAvailable) {
		this.disponibilidadeProduto = productAvailable;
	}
	
	//REALIZAR CADASTRO
	public boolean registerMenu(){
		dao = new MenuDAO();	
		to = new MenuTo();
		to.setIdProduct(idProduto);
		to.setProductName(nomeProduto);
		to.setProductDescription(descricaoProduto);
		to.setProductUniqueAmount(valorUnitarioProduto);
		to.setProductAvailable(disponibilidadeProduto);
		to.setProductType(tipoProduto);
        return dao.register(to);	
	}	
	
	//CONSULTAR COM ID TABLE
	public List<Menu> consultMenu(int productId) {
		dao = new MenuDAO();
		return dao.consult(productId);
	}
	
	//CONSULTAR COM NOME TABLE
	public List<Menu> consultMenu(String name) {
		dao = new MenuDAO();		
		return dao.consult(name);
	}
	
	//CONSULTAR TODOS TABLE
	public List<Menu> consultMenu() {
		dao = new MenuDAO();		        
        for(Menu menu : dao.consult()) {
			  System.out.println(menu.getProductName());
		}
        return dao.consult();
	}
	
	//EXCLUIR
	public boolean deleteUser(int idProduct){
		dao = new MenuDAO();			  
		to  = new MenuTo();
		to.setIdProduct(idProduct);
        return dao.delete(to);	
	}
	
	//ALTERAR
	public boolean changeMenu(int idProduct){
		dao = new MenuDAO();			  
		to  = new MenuTo();
		to.setIdProduct(getIdProduct());
		to.setProductName(getProductName());
		to.setProductDescription(getProductDescription());
		to.setProductUniqueAmount(getProductUniqueAmount());
		to.setProductAvailable(isProductAvailable());		
		to.setProductType(getProductType());
        return dao.change(to);	
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoProduto == null) ? 0 : descricaoProduto.hashCode());
		result = prime * result + (disponibilidadeProduto ? 1231 : 1237);
		result = prime * result + idProduto;
		result = prime * result + ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		result = prime * result + tipoProduto;
		long temp;
		temp = Double.doubleToLongBits(valorUnitarioProduto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (descricaoProduto == null) {
			if (other.descricaoProduto != null)
				return false;
		} else if (!descricaoProduto.equals(other.descricaoProduto))
			return false;
		if (disponibilidadeProduto != other.disponibilidadeProduto)
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (nomeProduto == null) {
			if (other.nomeProduto != null)
				return false;
		} else if (!nomeProduto.equals(other.nomeProduto))
			return false;
		if (tipoProduto != other.tipoProduto)
			return false;
		if (Double.doubleToLongBits(valorUnitarioProduto) != Double.doubleToLongBits(other.valorUnitarioProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cardapio [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", descricaoProduto="
				+ descricaoProduto + ", valorUnitarioProduto=" + valorUnitarioProduto + ", disponibilidadeProduto="
				+ disponibilidadeProduto + ", tipoProduto=" + tipoProduto + "]";
	}

	

}
