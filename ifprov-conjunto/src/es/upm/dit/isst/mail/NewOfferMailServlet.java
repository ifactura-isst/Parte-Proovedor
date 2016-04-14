package es.upm.dit.isst.mail;

import java.io.IOException;
import java.util.ArrayList;
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

public class NewOfferMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("llego mail serv");
		UserService userService = UserServiceFactory.getUserService();
		String user = userService.getCurrentUser().getEmail();
		
		String description = req.getParameter("description");
		String title = req.getParameter("title");
		String price = req.getParameter("price");
		String service = req.getParameter("service");

		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(
					"noreply@ifactura-conjunto.appspotmail.com",
					"iFactura"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user, user));
			msg.setSubject("Nueva oferta disponible en iFactura");
			String msgBody = "Se ha publicado una nueva oferta: \n \n - Titulo de la oferta: "
					+ title + "\n - Descrpicion de la oferta : " + description
					+ "\n - Precio: " + price +"€\n - Servicio ofertado: "+service+"\n";

			msgBody += System.getProperty("line.separator") + "Un saludo,"
					+ System.getProperty("line.separator") + "El equipo de iFactura";
			msg.setText(msgBody);
			Transport.send(msg);
			System.out.println(msgBody);
			resp.sendRedirect("/ofertas");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
	}

}