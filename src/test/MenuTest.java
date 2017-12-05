package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.MenuDAO;
import model.Menu;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuTest {
	Menu menu, copy;
	static int id = 0;
	
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		menu = new Menu(id, "Salada", "Salada", 22.0, true, 1);
		copy 	 = new Menu(id, "Salada", "Salada", 22.0, true, 1);
		System.out.println(menu);
		System.out.println(copy);
		System.out.println(id);
	}
	
	@Test
	public void test00Load() {
		System.out.println("carregar");

		Menu fixture = new Menu(1, "Macarrão", "Macarronada com queijo", 22, true, 1);
		MenuDAO novo = new MenuDAO(1, null, null, 0, false, 0);
		novo.consult(1);		
		System.out.println(fixture);
		System.out.println(novo);
		//assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Create() {
		System.out.println("criar");
		menu.registerMenu();
		id = menu.getIdProduct();
		System.out.println(id);
		//copy.IdProduct(id);
		//assertEquals("testa criacao", menu, copy);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		menu.setProductName("Teste");
		copy.setProductName("Teste");
		menu.changeMenu(1);
		menu.consultMenu(1);
		//assertEquals("testa atualizacao", menu, copy);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copy.setIdProduct(-1);
		copy.setProductName(null);
		copy.setProductDescription(null);
		copy.setProductUniqueAmount(0);
		copy.setProductAvailable(false);
		copy.setProductType(0);
		menu.deleteUser(1);
		menu.consultMenu(1);
		//assertEquals("testa exclusao", cardapio, copia);
	}

}
