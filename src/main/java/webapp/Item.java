package webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;

/**
 * Servlet implementation class Item
 */
@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
    	this.dao = new DAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id").isEmpty()){
			response.sendRedirect("/Items");
		}
		String itemJson = dao.getItem(new Integer(request.getParameter("id")));
		response.getWriter().append(itemJson).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = new Integer(request.getParameter("id"));
		if(id != null){
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			if(!name.isEmpty()){
				dao.updateName(id, name);
			}
			if(!category.isEmpty()){
				dao.updateCategory(id, category);
			}
			if(!price.isEmpty()){
				dao.updatePrice(id, price);
			}
		}
		doGet(request, response);
	}

}
