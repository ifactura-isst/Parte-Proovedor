package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;
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

import es.upm.dit.isst.offers.dao.OfferDAO;
import es.upm.dit.isst.offers.dao.OfferDAOImpl;
import es.upm.dit.isst.offers.model.Offer;

public class AddOffer extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// //////////CREATE RESOURCE///////////////////////
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String title = checkNull(req.getParameter("title"));
		String Description = checkNull(req.getParameter("description"));
		int price = Integer.parseInt(req.getParameter("price"));
		String service = checkNull(req.getParameter("service"));
		OfferDAO dao = OfferDAOImpl.getInstance();
		dao.add(title, Description, user, price, service);
		System.out.println("a�adida oferta con titulo y descrip:"+title+" "+Description+" y USer:"+user+"precio: "+price+"servicio: "+service);
		PrintWriter out = resp.getWriter();
		req.getSession().setAttribute("dialogo", "Oferta creada Correctamente!");

		resp.sendRedirect("/mandamail?title=" + title + "&description="
				+ Description + "&price=" + price + "&service=" + service);

		// resp.sendRedirect("/main");
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	
		if (user == null){
			resp.sendRedirect("/"); //si no estoy logueado me devuelve al inicio
			
		} else{
			RequestDispatcher view = req.getRequestDispatcher("AddOffer.jsp");
	        view.forward(req, resp);
		}
		
		
		
	}

}