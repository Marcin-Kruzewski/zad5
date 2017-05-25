package webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;

/**
 * Servlet implementation class products
 */
@WebServlet("/Items")
public class Items extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Items() {
    	this.dao = new DAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort;
		if (request.getParameter("sort") != null){
			sort = request.getParameter("sort");
		} else {
			sort = "name";
		}
		String category;
		String name;
		String fromprice = "0";
		String toprice = "99999999999999999999999";
		if (request.getParameter("category") != null){
			category = request.getParameter("category");
			response.getWriter().append(this.dao.getItemsByCategory(category)).append(request.getContextPath());
		}else if (request.getParameter("name") != null){
			name = request.getParameter("name");
			response.getWriter().append(this.dao.getItemsByName(name)).append(request.getContextPath());
		}else if (request.getParameter("fromprice") != null || request.getParameter("toprice") != null){
			if(request.getParameter("fromprice") != null) fromprice = request.getParameter("fromprice");
			if(request.getParameter("toprice") != null) toprice = request.getParameter("toprice");
			response.getWriter().append(this.dao.getItemsByPrice(fromprice, toprice)).append(request.getContextPath());
		}else{
			response.getWriter().append(this.dao.getItems(sort)).append(request.getContextPath());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao.addItem(request);
		doGet(request, response);
	}

}
