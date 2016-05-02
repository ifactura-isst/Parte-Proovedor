package es.upm.dit.isst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.facturas.dao.FacturasDAO;
import es.upm.dit.isst.facturas.dao.FacturasDAOImpl;
import es.upm.dit.isst.facturas.model.Factura;
import es.upm.dit.isst.offers.dao.OfferDAO;
import es.upm.dit.isst.offers.dao.OfferDAOImpl;
import es.upm.dit.isst.offers.model.Offer;

public class StatsServlet extends HttpServlet{
	
	private static final Long serialVersionUID = 1L;
	public ArrayList<String> eliminaduplicados(ArrayList<String> lista) {

		HashSet<String> hs = new HashSet<String>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
		return lista;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		List<Factura> facturas = new ArrayList<Factura>();
		facturas = dao.getFacturas();
		ArrayList<String> usuarios = new ArrayList<String>();
		ArrayList<String> tipos = new ArrayList<String>();
		
		for(int i = 0; i < facturas.size(); i++){
			usuarios.add(facturas.get(i).getUser().getEmail());
		}
		for(int i = 0; i < facturas.size(); i++){
			tipos.add(facturas.get(i).getTipo());
		}
		Set<String> buscatipos = new HashSet<String>(tipos);
		req.getSession().setAttribute("luz", 0);
		req.getSession().setAttribute("agua", 0);
		req.getSession().setAttribute("gas", 0);
		req.getSession().setAttribute("telefono", 0);
		
        for (String key : buscatipos) {
            System.out.println(key + " : " + Collections.frequency(tipos, key));
            req.getSession().setAttribute(key, Collections.frequency(tipos, key));
        }
		usuarios = eliminaduplicados(usuarios);
		req.getSession().setAttribute("numfacturas", facturas.size());
		req.getSession().setAttribute("numusuarios", usuarios.size());
		
	
		if (user == null){
			resp.sendRedirect("/"); //si no estoy logueado me devuelve al inicio
			
		} else{
			RequestDispatcher view = req.getRequestDispatcher("StatsDashboard.jsp");
	        view.forward(req, resp);
		}
		
		
		
	}

}
