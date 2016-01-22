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

/**
 * Servlet implementation class Main
 * Charge l'ensemble des jours pr�sent en base
 * Accessible uniquement � un utilisateur connect�
 * Mapp�e sur le path /main et sert la vue main.jsp
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
	 * R�cup�re l'ensemble des jours pr�sents en base
	 * Et les transmet � la vue main.jsp pour affichage
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if(session.getAttribute("guest") == null){
			response.sendRedirect("signin");
		}else{
			
			List<Day> days = RepositoryManager.getDayManager().findAll();			
			request.setAttribute("days", days);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/main.jsp" ).forward( request, response );
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
