package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

public class UpdateSubasta extends HttpServlet{
	
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

		SubastaDAO dao = SubastaDAOImpl.getInstance();

		Subasta subasta = dao.getSubasta(Long.parseLong(offerId));
		String description = subasta.getDescription();
		String title = subasta.getTitle();
		int userMax = subasta.getuserMax();

		req.getSession().setAttribute("title", title);
		req.getSession().setAttribute("description", description);
		req.getSession().setAttribute("userMax", userMax);
		
		req.getSession().setAttribute("id", offerId);
		req.getSession().setAttribute("user", user);

		req.getSession().setAttribute("urlLinktext", urlLinktext);

		RequestDispatcher view = req
				.getRequestDispatcher("UpdateSubasta.jsp");
		view.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
			PrintWriter out = resp.getWriter();

			SubastaDAO daosubasta = SubastaDAOImpl.getInstance();

			// UserService userService = UserServiceFactory.getUserService();
			// String user = userService.getCurrentUser().getUserId();
			
			String subastaId = req.getParameter("subastaId");
			String title = checkNull(req.getParameter("title"));
			String description = checkNull(req.getParameter("description"));
			int userMax = Integer.parseInt(req.getParameter("userMax"));
			Subasta subasta = daosubasta.getSubasta(Long.parseLong(subastaId));
			
			boolean state = subasta.getState();
			int userApuntados = subasta.getuserApuntados();
			ArrayList<String> customers = subasta.getUsuariosApuntados();
			
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			
			System.out.println("Subasta datos a update: "+subastaId+title+description);

			try {
				System.out.println("Subasta datos a update: "+subastaId+title+description+userMax);
				daosubasta.update(Long.parseLong(subastaId), state, userMax, userApuntados,title,description,user,customers);
				req.getSession().setAttribute("dialogo", "Subasta Modificada Correctamente!");

			} finally {
				out.println("<script>location='/pujas';</script>");
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
