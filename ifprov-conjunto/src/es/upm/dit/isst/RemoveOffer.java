package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.offers.dao.OfferDAO;
import es.upm.dit.isst.offers.dao.OfferDAOImpl;



public class RemoveOffer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private void alertHTML(PrintWriter out, String message) throws IOException {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + message + "');");
		out.println("</script>");

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		// /////COMPROBACION ADMIN///////////
		
			String id = req.getParameter("id");
			OfferDAO dao = OfferDAOImpl.getInstance();
			dao.remove(Long.parseLong(id));
			//alertHTML(out, "Reserva eliminada!!");
			req.getSession().setAttribute("dialogo", "Oferta Eliminada Correctamente!");

		
		out.println("<script>location='/ofertas';</script>");

		
	}
}
	

