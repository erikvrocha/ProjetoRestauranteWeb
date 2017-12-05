package Interface;

import java.util.ResourceBundle;

public class MainRestauranteView {
	
	
	public static void main(String[]args)
	{
		ResourceBundle  bn=null;
		RestauranteView restaurante;		
		bn = ResourceBundle.getBundle("inter");		
		restaurante = new RestauranteView(bn);
	}
}