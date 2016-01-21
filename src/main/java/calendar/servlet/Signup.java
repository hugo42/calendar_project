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
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class Signup
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("name") != null &&
				request.getParameter("email") != null &&
				request.getParameter("password") != null &&
				request.getParameter("passwordConfirm") != null &&
						request.getParameter("password").trim().equals(request.getParameter("passwordConfirm").trim())
			){
			
			EntityManager em = new EntityManager();
			
			Guest guest = new Guest();
			guest.setName(request.getParameter("name"));
			guest.setEmail(request.getParameter("email"));
			guest.setPassword(request.getParameter("password"));
			em.persist(guest);
			em.flush();
			
			/**
			 * Signin
			 */
			HttpSession session = request.getSession(true);
			session.setAttribute("guest", guest);
			session.setMaxInactiveInterval(10*60);
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("main");
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.sendRedirect("signup");
		}
	}

}
