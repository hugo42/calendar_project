package calendar.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.util.comparator.InstanceComparator;

import calendar.business.Guest;
import calendar.dao.EntityManager;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class Signin
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		if( request.getParameter("email") != null &&
				request.getParameter("password") != null
			){
			
			RepositoryManager rm = new RepositoryManager();
			
			String email = request.getParameter("email");
			Guest guest = rm.getGuestManager().findOneByEmail(email);
			if(guest != null){
				if(guest.getPassword().trim().equals(request.getParameter("password").trim())){
					response.setStatus(HttpServletResponse.SC_OK);
//					TODO : set user in session
				}
			}
		}
		
		if(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST){
			response.sendRedirect("signin");
		}else{
			response.sendRedirect("main");
		}
	}

}
