package calendar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.business.Guest;
import calendar.dao.RepositoryManager;
import calendar.services.EmailService;

/**
 * Servlet implementation class CheckEmail
 * Est mappée sur le path /check-email et ne sert pas de page
 * Requêtée via Ajax pour tester si un utilisateur avec son email en base
 */
@WebServlet("/check-email")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Test si un utilisateur existe avec l'email envoyé en paramètre
	 * Retourne un status code 200 s'il existe
	 * Retourne un status code 403 s'il n'existe pas
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		if(request.getParameter("email") != null){
			if(EmailService.emailExists(request.getParameter("email"))){
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}
		doGet(request, response);
	}

}
