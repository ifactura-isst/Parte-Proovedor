package es.upm.dit.isst.mailsubasta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


import es.upm.dit.isst.subastas.dao.SubastaDAO;
import es.upm.dit.isst.subastas.dao.SubastaDAOImpl;
import es.upm.dit.isst.subastas.model.Subasta;

public class NewSubastaMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ArrayList<String> eliminaduplicados(ArrayList<String> lista) {

		HashSet<String> hs = new HashSet<String>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
		return lista;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = new ArrayList<Subasta>();
		subastas = dao.readSubastas();
		for(Subasta subasta: subastas){
			if(subasta.getState()==true){
				String address = "";
				for(String user: subasta.getUsuariosApuntados()){
					address += user+","; 
				}
				String description = subasta.getDescription();
				String title = subasta.getTitle();
				
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				Message msg = new MimeMessage(session);
				try {
					msg.setFrom(new InternetAddress("noreply@ifactura-conjunto.appspotmail.com", "iFactura"));
				
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
				msg.setSubject("Subasta finalizada en iFactura");
				
				String msgBody = "Se ha finalizado una subasta: \n \n - Titulo de la subasta: " + title
						+ "\n - Descrpicion de la subasta: " + description + "\n";

				msgBody += System.getProperty("line.separator") + "Un saludo," + System.getProperty("line.separator")
						+ "El equipo de iFactura";
				msg.setText(msgBody);
				Transport.send(msg);
				System.out.println(msgBody);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		System.out.println("llego mail serv");		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

}