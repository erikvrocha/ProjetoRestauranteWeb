package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Menu;

@WebServlet("/CrudMenu.do")
public class CrudMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("nome");
		String description = request.getParameter("descricao");
		Double amount = Double.parseDouble(request.getParameter("valor"));
		boolean available = Boolean.parseBoolean(request.getParameter("dispo"));
		int type = Integer.parseInt(request.getParameter("tipo"));

		Menu menu = new Menu();
		menu.setIdProduct(id);
		menu.setProductName(name);
		menu.setProductDescription(description);
		menu.setProductUniqueAmount(amount);
		menu.setProductAvailable(available);
		menu.setProductType(type);

		if (menu.registerMenu()) {
			System.out.println("Cadastrou com sucesso");

			request.setAttribute("cardapio", menu);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Cardapio.jsp");

			dispatcher.forward(request, response);

		}

	}
}
