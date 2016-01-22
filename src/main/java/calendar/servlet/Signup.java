package calendar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antlr.debug.NewLineEvent;
import calendar.business.Guest;
import calendar.dao.EntityManager;
import calendar.dao.HibernateFactory;
import calendar.dao.RepositoryManager;
import calendar.services.AuthenticationService;

/**
 * Servlet implementation class Signup
 * Est mappée sur le path /signup
 * En GET sert la vue signup.jsp
 * En POST créer un nouvel utilisateur en base
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/signup.jsp" ).forward( request, response );
	}

	/**
	 * Appel le service d'authentification en vue de créer l'utilisateur
	 * Succes : Connecte l'utilisateur et le redirige sur la servlet main
	 * Error : Renvoie l'utilisateur sur la page d'inscription et affiche l'erreur
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = AuthenticationService.signup(
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("passwordConfirm"),
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
