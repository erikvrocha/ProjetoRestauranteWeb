package to;

import dao.MenuDAO;

public class MenuTo {
	private String nomeProduto, descricaoProduto;
	private int idProduto, tipoProduto;
	private double valorUnitarioProduto;
	private boolean disponibilidadeProduto;
	
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
	
}
