package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;

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

public class UpdateOffer extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private void alertHTML(PrintWriter out, String message) throws IOException {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + message + "');");
		out.println("</script>");

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// //////////USER/////////////////////////
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		boolean userAdmin = false;
		if (userService.isUserLoggedIn()) {
			userAdmin = userService.isUserAdmin();
		}
		req.getSession().setAttribute("userAdmin", userAdmin);
		String urlLinktext = "Login";
		if (user != null) {
			urlLinktext = "Logout";
		}
		String offerId = req.getParameter("id");

		OfferDAO dao = OfferDAOImpl.getInstance();

		Offer offer = dao.getOffer(Long.parseLong(offerId));
		String description = offer.getDescription();
		String title = offer.getTitle();

		req.getSession().setAttribute("title", title);
		req.getSession().setAttribute("description", description);
		req.getSession().setAttribute("id", offerId);
		req.getSession().setAttribute("user", user);

		req.getSession().setAttribute("urlLinktext", urlLinktext);

		RequestDispatcher view = req
				.getRequestDispatcher("UpdateOffer.jsp");
		view.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
			PrintWriter out = resp.getWriter();

			OfferDAO daoffer = OfferDAOImpl.getInstance();

			// UserService userService = UserServiceFactory.getUserService();
			// String user = userService.getCurrentUser().getUserId();
			String offerId = req.getParameter("offerId");
			String title = checkNull(req.getParameter("title"));
			String description = checkNull(req.getParameter("description"));
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			
			System.out.println("Oferta datos a update: "+offerId+title+description);

			try {
				System.out.println("Oferta datos a update: "+offerId+title+description);
				daoffer.update(Long.parseLong(offerId), title,
						description, user);
				req.getSession().setAttribute("dialogo", "Oferta Modificada Correctamente!");

			} finally {
				out.println("<script>location='/ofertas';</script>");
			}
		

		

	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	private String check(String s) {
		if (s.equals("")) {
			return "1";
		}
		return s;
	}

}
