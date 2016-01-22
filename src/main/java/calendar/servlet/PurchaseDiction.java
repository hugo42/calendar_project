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
import calendar.services.AuthenticationService;
import calendar.services.PurchaseService;

/**
 * Servlet implementation class PurchaseDiction
 * Traite l'enregistrement d'un dicton et l'achat de ce dernier par l'utilisateur connect�
 * Est mapp�e sur le path /purchaseDiction, redirige vers la servlet main en cas de succes 
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
	 * Gestion de concurrence d'acc�s gr�ce au synchronized
	 * Persiste un dicton en base
	 * G�n�re un nouveau Purchase
	 * Lui affecte l'id de l'utilisateur logger
	 * Ainsi que l'id du dicton nouvellement cr��
	 * Et enfin l'id du jour concern� par l'achat
	 * G�re aussi la d�duction du co�t de l'achat au solde de l'utilisateur
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String error = PurchaseService.purchaseDiction(request.getParameter("diction"),
				request.getParameter("day"),
				request.getSession());
	
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
