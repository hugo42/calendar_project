package calendar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.business.Day;
import calendar.business.Diction;
import calendar.business.Guest;
import calendar.business.Picture;
import calendar.business.Purchase;
import calendar.dao.EntityManager;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class PurchaseDiction
 */
@WebServlet("/purchaseDiction")
public class PurchaseDiction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseDiction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/error.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("diction") != null &&
				request.getParameter("day") != null
			){
			
			Integer day = Integer.parseInt(request.getParameter("day"));
			Day pDay = RepositoryManager.getDayManager().find(day);
			
			Guest pGuest = (Guest) request.getSession(true).getAttribute("guest");
			if( pDay.getPurchase() == null){
				
				if(pGuest.getBalance() >= 2 ){

					Diction diction= new Diction();
					diction.setContent(request.getParameter("diction"));
					
					Purchase purchase = new Purchase();
					purchase.setDay(pDay);
					purchase.setFeature(diction);
					purchase.setGuest(pGuest);
					
					pGuest.setBalance(pGuest.getBalance() - 2);
					
					EntityManager.persist(diction);
					EntityManager.persist(purchase);
					
					EntityManager.flush();
					response.setStatus(HttpServletResponse.SC_OK);
				}else{
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					request.setAttribute("errors", "Vous n'avez pas les moyens...");
				}
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				request.setAttribute("errors", "Une erreur est survenue, veuillez réessayer plus tard...");
			}
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("errors", "Une erreur est survenue, veuillez réessayer plus tard...");
		}

		if(response.getStatus() == HttpServletResponse.SC_OK){
			response.sendRedirect("main");
		}else{
			doGet(request, response);
		}
	}
}
