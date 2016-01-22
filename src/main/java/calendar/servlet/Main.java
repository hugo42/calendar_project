package calendar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.business.Day;
import calendar.dao.RepositoryManager;
import calendar.services.AuthenticationService;

/**
 * Servlet implementation class Main
 * Charge l'ensemble des jours présent en base
 * Accessible uniquement à un utilisateur connecté
 * Mappée sur le path /main et sert la vue main.jsp
 */
@WebServlet(name = "main", urlPatterns = { "/main" })
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Récupère l'ensemble des jours présents en base
	 * Et les transmet à la vue main.jsp pour affichage
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (AuthenticationService.isConnected(request.getSession())){
			
			request.setAttribute("days", RepositoryManager.getDayManager().findAll());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/main.jsp" ).forward( request, response );
		}else{
			response.sendRedirect("signin");
		}
	}

	/**
	 * Redirige vers doGet()
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
