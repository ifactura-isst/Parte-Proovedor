package es.upm.dit.isst.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.facturas.dao.FacturasDAO;
import es.upm.dit.isst.facturas.dao.FacturasDAOImpl;
import es.upm.dit.isst.facturas.model.Factura;

public class NewOfferMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ArrayList<String> eliminaduplicados(ArrayList<String> lista) {

		HashSet<String> hs = new HashSet<String>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
		return lista;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		System.out.println("llego mail serv");
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		List<Factura> facturas = new ArrayList<Factura>();
		facturas = dao.getAllFacturas();
		ArrayList<String> usuarios = new ArrayList<String>();
		String description = req.getParameter("description");
		String title = req.getParameter("title");
		String price = req.getParameter("price");
		String service = req.getParameter("service");

		for (int i = 0; i < facturas.size(); i++) {
			
			usuarios.add(facturas.get(i).getUser().getEmail());
			
		}
		usuarios = eliminaduplicados(usuarios);

		
		for (int i = 0; i < usuarios.size(); i++) {
			try {
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("noreply@ifactura-conjunto.appspotmail.com", "iFactura"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usuarios.get(i), usuarios.get(i)));
				msg.setSubject("Nueva oferta disponible en iFactura");
				String msgBody = "Se ha publicado una nueva oferta: \n \n - Titulo de la oferta: " + title
						+ "\n - Descrpicion de la oferta : " + description + "\n - Precio: " + price
						+ "�\n - Servicio ofertado: " + service + "\n";

				msgBody += System.getProperty("line.separator") + "Un saludo," + System.getProperty("line.separator")
						+ "El equipo de iFactura";
				msg.setText(msgBody);
				Transport.send(msg);
				System.out.println(msgBody);
				resp.sendRedirect("/ofertas");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

}