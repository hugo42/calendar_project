package calendar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.business.Guest;
import calendar.dao.GuestDao;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup/check-email")
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
//		response.getWriter().append(request.getRequestURL()+"<br>");

		String email = request.getAttribute("email").toString();
		RepositoryManager rm = new RepositoryManager();
		Guest guest = rm.getGuestManager().findOneByEmail(email);
		
		if(guest != null){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}else{
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
