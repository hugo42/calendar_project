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
 * Traite l'enregistrement d'un dicton et l'achat de ce dernier par l'utilisateur connecté
 * Est mappée sur le path /purchaseDiction, redirige vers la servlet main en cas de succes 
 * ou sert la vue error.jsp en cas d'erreur
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
		processRequest(request, response);
	}
	
	/**
	 * Gestion de concurrence d'accès grâce au synchronized
	 * Persiste un dicton en base
	 * Génère un nouveau Purchase
	 * Lui affecte l'id de l'utilisateur logger
	 * Ainsi que l'id du dicton nouvellement créé
	 * Et enfin l'id du jour concerné par l'achat
	 * Gère aussi la déduction du coût de l'achat au solde de l'utilisateur
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
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
