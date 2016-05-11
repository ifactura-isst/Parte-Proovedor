package es.upm.dit.isst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.subastas.dao.SubastaDAO;
import es.upm.dit.isst.subastas.dao.SubastaDAOImpl;
import es.upm.dit.isst.subastas.model.Subasta;

public class SubastasDashboard extends HttpServlet{
	
	private static final Long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Object lang = req.getSession().getAttribute("idioma");
		String idioma = "";
		String language = lang.toString();
		idioma = language;
		
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = new ArrayList<Subasta>();
		subastas = dao.listSubastasByUser(user);
		req.getSession().setAttribute("subastas",
				new ArrayList<Subasta>(subastas));
		
	
		if (user == null){
			resp.sendRedirect("/"); //si no estoy logueado me devuelve al inicio
			
		} else{
			if (idioma.equals("es")){
			RequestDispatcher view = req.getRequestDispatcher("SubastasDashboard.jsp");
	        view.forward(req, resp);
			}
			if (idioma.equals("en")){
			RequestDispatcher view = req.getRequestDispatcher("SubastasDashboard_en.jsp");
	        view.forward(req, resp);
			}
		}
		
		
		
	}

}
