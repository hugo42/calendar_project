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
import calendar.services.AuthenticationService;

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
	 * Appel le service d'authentification en vue de connecter l'utilisateur
	 * Succes : Connecte l'utilisateur et le redirige sur la servlet main
	 * Error : Renvoie l'utilisateur sur la page de connexion et affiche l'erreur
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = AuthenticationService.signin(request.getParameter("email"),
					request.getParameter("password"),
					request.getSession(true));
		
		if(error != null){
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("error", error);
			doGet(request, response);
		}else{
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("main");
		}
	}
}
