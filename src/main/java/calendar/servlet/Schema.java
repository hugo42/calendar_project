package calendar.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import calendar.business.Day;
import calendar.business.Diction;
import calendar.business.Guest;
import calendar.business.Picture;
import calendar.business.Purchase;
import calendar.dao.EntityManager;
import calendar.dao.HibernateFactory;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class SchemaUpdate
 * Sert uniquement à la génération de données de test
 * Est mappés sur le path /create-date ne renvoie rien 
 */
@WebServlet("/create-data")
public class Schema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schema() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Génération des données de test pour la base
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Génération de Guest
		 */
		Guest g = new Guest();
		g.setName("Hugo Delphin-Poulat");
		g.setEmail("hugo@gmail.com");
		g.setPassword("123");
		EntityManager.persist(g);
		
		g = new Guest();
		g.setName("Olivier Dupont");
		g.setEmail("olivier@gmail.com");
		g.setPassword("123");
		EntityManager.persist(g);

		
		/**
		 * Génération des jours
		 * ATTENTION : Calendar.JANUARY = 0, Calendar.FEBRUARY = 1 etc...
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		while (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
			
			@SuppressWarnings({ "deprecation", "deprecation" })
			Date date = new Date(
					calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH)
			);
			Day day = new Day();
			day.setDayDate(date);
			System.out.println(day.getTextDate());
			EntityManager.persist(day);
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		/**
		 * Génaration tests de features
		 */
		
		Picture picture = new Picture();
		picture.setSource("source picture");
		EntityManager.persist(picture);
		
		Diction diction= new Diction();
		diction.setContent("bla bla bla");
		EntityManager.persist(diction);
		
		EntityManager.flush();
		
		
		/**
		 * Génaration tests de puraches
		 */
		
//		
//		Purchase avec une image
//		
		Purchase purchase = new Purchase();
		purchase.setGuest(
				RepositoryManager.getGuestManager().find(1)
			);
		purchase.setDay(
				RepositoryManager.getDayManager().find(1)
			);
		
		purchase.setFeature(
				RepositoryManager.getPictureManager().find(1)
			);
		EntityManager.persist(purchase);
		
//		
//		Purchase avec un dicton
//		
		purchase = new Purchase();
		purchase.setGuest(
				RepositoryManager.getGuestManager().find(1)
			);
		purchase.setDay(
				RepositoryManager.getDayManager().find(2)
			);
		
		purchase.setFeature(
				RepositoryManager.getDictionManager().find(2)
			);
		EntityManager.persist(purchase);
		
		EntityManager.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
