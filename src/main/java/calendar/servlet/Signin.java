package calendar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.business.Guest;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class Signin
 * Est mappée sur le path /signin 
 * En POST log un utilisateur après test de présence en base
 * En GET sert la vue signin.jsp
 */
@WebServlet(name = "signin", urlPatterns = { "/signin" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/signin.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Gére la sauvegarde de l'utilisateur en session en vue d'une connexion
	 * Effectue au préalable un test sur le mail et le password
	 * Et redirige sur la servlet main en cas de succes ou sur la vue error.jsp en cas d'erreur
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("email") != null &&
				request.getParameter("password") != null
			){
			
			String email = request.getParameter("email");
			Guest guest = RepositoryManager.getGuestManager().findOneByEmail(email);
			if(guest != null){
				if(guest.getPassword().trim().equals(request.getParameter("password").trim())){
					HttpSession session = request.getSession(true);
					session.setAttribute("guest", guest);
					session.setMaxInactiveInterval(10*60);
					response.setStatus(HttpServletResponse.SC_OK);
				}else{
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					request.setAttribute("errors", "Mot de passe incorrect...");
				}
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				request.setAttribute("errors", "Cette adresse mail ne correspond à aucun utilisateur...");
			}
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("errors", "Une erreur est survenue, veuillez réessayer...");
		}
		
		
		if(response.getStatus() == HttpServletResponse.SC_OK){
			response.sendRedirect("main");
		}else{
			doGet(request, response);
		}
	}

}
