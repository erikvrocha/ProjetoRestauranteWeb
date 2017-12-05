package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Menu;
import to.MenuTo;

public class MenuDAO {
	private String nomeProduto, descricaoProduto;
	private int tipoProduto;
	private int idProduto;
	private double valorUnitarioProduto;
	private boolean disponibilidadeProduto;
	
	public MenuDAO() {
		
	}
	public MenuDAO(int idProduct, String productName, String productDescription, double productUniqueAmount,
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
	public void setIdProduto(int idProduct) {
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
	
	//********************CRUD MENU*********************************//
	//CADASTRAR
	public boolean register(MenuTo to) {
		
		String sqlInsert = 
				"INSERT INTO CARDAPIO(idProduto, nomeProduto, descricaoProduto, valorUnitarioProduto, disponibilidadeProduto, tipoProduto) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stm = null;
		Connection conn = null;
		        
		try {			
			// FAZENDO CONEXÃO COM O BANCO DE DADOS		
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlInsert);
			stm.setInt		(1, to.getIdProduct());
			stm.setString	(2, to.getProductName());
			stm.setString	(3, to.getProductDescription());
			stm.setDouble	(4, to.getProductUniqueAmount());
			stm.setBoolean	(5, to.isProductAvailable());
			stm.setInt		(6, to.getProductType());
						
			stm.execute();
			conn.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
		finally {
			if (stm != null) {
				try {
					stm.close();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//EXCLUIR
	public boolean delete(MenuTo to) {
		String sqlDelete = "DELETE FROM Cardapio WHERE idProduto = " + to.getIdProduct();
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// FAZENDO CONEXÃO COM BD			
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);
			
			stm = conn.prepareStatement(sqlDelete);
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}		
		return false;
	}
	
	//ALTERAR PRODUTO
	public boolean change(MenuTo to) {		
		String sqlAlter = "UPDATE CARDAPIO SET "
						+ "nomeProduto = ?, descricaoProduto = ?, valorUnitarioProduto = ?, disponibilidadeProduto = ?, tipoProduto = ? WHERE idProduto = " + to.getIdProduct();
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// FAZENDO CONEXÃO COM BD			
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);						
			stm = conn.prepareStatement(sqlAlter);
			stm.setString (1, to.getProductName());
			stm.setString (2, to.getProductDescription());
			stm.setDouble (3, to.getProductUniqueAmount());
			stm.setBoolean(4, to.isProductAvailable());
			stm.setInt	  (5, to.getProductType());
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//CONSULTAR COM CÓDIGO
	public List<Menu> consult(int productId) {		
		List<Menu> produtos = new ArrayList<Menu>();
		String sqlSelect = "SELECT * FROM CARDAPIO WHERE idProduto = " + productId;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// FAZENDO CONEXÃO COM BD				
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);
			Menu menu = new Menu();
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 //menu = new Menu();
				 menu.setIdProduct(rs.getInt("idProduto"));
				 menu.setProductName(rs.getString("nomeProduto"));
				 menu.setProductDescription(rs.getString("descricaoProduto"));
				 menu.setProductUniqueAmount(rs.getInt("valorUnitarioProduto"));
				 menu.setProductAvailable(rs.getBoolean("disponibilidadeProduto"));
				 menu.setProductType(rs.getInt("tipoProduto"));
				 setIdProduto(rs.getInt("idProduto"));
				 setProductName(rs.getString("nomeProduto"));
				 setProductDescription(rs.getString("descricaoProduto"));
				 setProductUniqueAmount(rs.getInt("valorUnitarioProduto"));
				 setProductAvailable(rs.getBoolean("disponibilidadeProduto"));
				 setProductType(rs.getInt("tipoProduto"));
				 produtos.add(menu);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 return produtos;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return produtos;
	}
	
	
	//CONSULTAR COM NOME
	public List<Menu> consult(String productName) {		
		List<Menu> produtos = new ArrayList<Menu>();
		String sqlSelect = "SELECT * FROM CARDAPIO WHERE nomeProduto = '" + productName + "'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// FAZENDO CONEXÃO COM BD				
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			while(rs.next()) {  
				 Menu menu = new Menu();
				 menu.setIdProduct(rs.getInt("idProduto"));
				 menu.setProductName(rs.getString("nomeProduto"));
				 menu.setProductDescription(rs.getString("descricaoProduto"));
				 menu.setProductUniqueAmount(rs.getInt("valorUnitarioProduto"));
				 menu.setProductAvailable(rs.getBoolean("disponibilidadeProduto"));
				 menu.setProductType(rs.getInt("tipoProduto"));
				 produtos.add(menu);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 return produtos;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return produtos;
	}
	
	//CONSULTAR TODOS
	public List<Menu> consult() {		
		List<Menu> produtos = new ArrayList<Menu>();

		String sqlSelect = "SELECT * FROM CARDAPIO";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			ConnectionFactory bd = new ConnectionFactory();
			conn = bd.makeConnection();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			while(rs.next()) {  
				 Menu menu = new Menu();
				 menu.setIdProduct(rs.getInt("idProduto"));
				 menu.setProductName(rs.getString("nomeProduto"));
				 menu.setProductDescription(rs.getString("descricaoProduto"));
				 menu.setProductUniqueAmount(rs.getDouble("valorUnitarioProduto"));
				 menu.setProductAvailable(rs.getBoolean("disponibilidadeProduto"));
				 menu.setProductType(rs.getInt("tipoProduto"));
				 
				 produtos.add(menu);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 
			 return produtos;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return produtos;
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
		MenuDAO other = (MenuDAO) obj;
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
		return "CardapioDAO [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", descricaoProduto="
				+ descricaoProduto + ", valorUnitarioProduto=" + valorUnitarioProduto + ", disponibilidadeProduto="
				+ disponibilidadeProduto + ", tipoProduto=" + tipoProduto + "]";
	}
}
