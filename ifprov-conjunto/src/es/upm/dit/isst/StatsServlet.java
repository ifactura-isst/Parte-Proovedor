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
		Object lang = req.getSession().getAttribute("idioma");
		String idioma = "";
		String language = lang.toString();
		idioma = language;
		
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		List<Factura> facturas = new ArrayList<Factura>();
		facturas = dao.getAllFacturas();
		ArrayList<String> usuarios = new ArrayList<String>();
		ArrayList<String> tipos = new ArrayList<String>();
		double consumoenero = 0;
		double consumofebrero = 0;
		double consumomarzo = 0;
		double consumoabril = 0;
		double consumomayo = 0;
		double consumojunio = 0;
		double consumojulio = 0;
		double consumoagosto = 0;
		double consumoseptiembre = 0;
		double consumooctubre = 0;
		double consumonoviembre = 0;
		double consumodiciembre = 0;
		
		
		
		for(int i = 0; i < facturas.size(); i++){
			usuarios.add(facturas.get(i).getUser().getEmail());
		}
		for(int i = 0; i < facturas.size(); i++){
			tipos.add(facturas.get(i).getEmpresa());
		}
		for(int i = 0; i < facturas.size(); i++){
			String[] fecha = facturas.get(i).getStartDate().split("/");
			String mes = fecha[1];
			if (mes.equals("01")){
				consumoenero += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("02")){
				consumofebrero += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("03")){
				consumomarzo += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("04")){
				consumoabril = facturas.get(i).getImporteTotal();
			}
			if (mes.equals("05")){
				consumomayo += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("06")){
				consumojunio = facturas.get(i).getImporteTotal();
			}
			if (mes.equals("07")){
				consumojulio += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("08")){
				consumoagosto += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("09")){
				consumoseptiembre += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("10")){
				consumooctubre += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("11")){
				consumonoviembre += facturas.get(i).getImporteTotal();
			}
			if (mes.equals("12")){
				consumodiciembre += facturas.get(i).getImporteTotal();
			}
		}
		
		Set<String> buscatipos = new HashSet<String>(tipos);
		req.getSession().setAttribute("Orange", 0);
		req.getSession().setAttribute("Movistar", 0);
		req.getSession().setAttribute("Vodafone", 0);
		req.getSession().setAttribute("Yoigo", 0);
		req.getSession().setAttribute("Enero", consumoenero);
		req.getSession().setAttribute("Febrero", consumofebrero);
		req.getSession().setAttribute("Marzo", consumomarzo);
		req.getSession().setAttribute("Abril", consumoabril);
		req.getSession().setAttribute("Mayo", consumomayo);
		req.getSession().setAttribute("Junio", consumojunio);
		req.getSession().setAttribute("Julio", consumojulio);
		req.getSession().setAttribute("Agosto", consumoagosto);
		req.getSession().setAttribute("Septiembre", consumoseptiembre);
		req.getSession().setAttribute("Octubre", consumooctubre);
		req.getSession().setAttribute("Noviembre", consumonoviembre);
		req.getSession().setAttribute("Diciembre", consumodiciembre);
		
		
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
			if (idioma.equals("es")){
			RequestDispatcher view = req.getRequestDispatcher("StatsDashboard.jsp");
	        view.forward(req, resp);
			}
			if (idioma.equals("en")){
			RequestDispatcher view = req.getRequestDispatcher("StatsDashboard_en.jsp");
	        view.forward(req, resp);
			}
		}
		
		
		
	}

}
