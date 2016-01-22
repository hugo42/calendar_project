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

/**
 * Servlet implementation class Signup
 * Est mapp�e sur le path /signup
 * En GET sert la vue signup.jsp
 * En POST cr�er un nouvel utilisateur en base
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * G�re la cr�ation d'un utilisateur en base
	 * Effectue au pr�alable des tests sur les champs replis par l'utilisateur
	 * V�rifie �galement que l'utilisateur n'existe pas d�j�
	 * Redirige sur la servlet main en cas de succces et vers la page error.jsp en cas d'erreur
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("name") != null &&
				request.getParameter("email") != null &&
				request.getParameter("password") != null &&
				request.getParameter("passwordConfirm") != null &&
						request.getParameter("password").trim().equals(request.getParameter("passwordConfirm").trim())
			){
			
			Guest g = RepositoryManager.getGuestManager().findOneByEmail(request.getParameter("email"));
			if(g == null){
				
				Guest guest = new Guest();
				guest.setName(request.getParameter("name"));
				guest.setEmail(request.getParameter("email"));
				guest.setPassword(request.getParameter("password"));
				EntityManager.persist(guest);
				EntityManager.flush();
				
				/**
				 * Signin
				 */
				HttpSession session = request.getSession(true);
				session.setAttribute("guest", guest);
				session.setMaxInactiveInterval(10*60);
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				request.setAttribute("errors", "Cette adresse mail est d�j� utilis�e par un autre utilisateur...");
			}
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("errors", "Une erreur est survenue, veuillez r�essayer...");
		}
		
		if(response.getStatus() == HttpServletResponse.SC_OK){
			response.sendRedirect("main");
		}else{
			doGet(request, response);
		}
	}

}
